from fastapi import FastAPI, Request
from fastapi.exceptions import RequestValidationError
from fastapi.responses import JSONResponse

from api.common import GenericResponse
from database import database, engine, Base
from routers import users, auth

app = FastAPI()

app.include_router(users.router)
app.include_router(auth.router)


@app.on_event("startup")
async def startup():
    await database.connect()


@app.on_event("shutdown")
async def shutdown():
    await database.disconnect()


@app.exception_handler(RequestValidationError)
async def validation_exception_handler(request: Request, exc: RequestValidationError):
    errors = exc.errors()
    error_details = {"errors": errors}
    response = GenericResponse[None](status="error", details=error_details)
    return JSONResponse(
        status_code=422,
        content=response.dict(),
    )


if __name__ == "__main__":
    import uvicorn

    Base.metadata.drop_all(bind=engine)  # TODO: Remove when testing is complete
    Base.metadata.create_all(bind=engine)
    uvicorn.run(app, port=8000)
