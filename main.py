from fastapi import FastAPI, Request
from fastapi.exceptions import RequestValidationError
from fastapi.responses import JSONResponse
from starlette.exceptions import HTTPException

from api.common import GenericResponse
from database import database, engine, Base
from routers import users, auth, campaigns

app = FastAPI()

app_version = "v1"

app.include_router(users.router, prefix="/api/"+app_version+"/users", tags=["users"])
app.include_router(auth.router, prefix="/api/"+app_version+"/auth", tags=["auth"])
app.include_router(campaigns.router, prefix="/api/"+app_version+"/campaigns", tags=["campaigns"])


@app.on_event("startup")
async def startup():
    await database.connect()


@app.on_event("shutdown")
async def shutdown():
    await database.disconnect()


@app.get("/api/"+app_version+"/status", response_model=GenericResponse[str])
async def get_status():
    return GenericResponse[str](details="All systems operational.")


@app.exception_handler(RequestValidationError)
async def validation_exception_handler(request: Request, exc: RequestValidationError):
    errors = exc.errors()
    error_details = {"errors": errors}
    response = GenericResponse[None](status="error", details=error_details)
    return JSONResponse(
        status_code=422,
        content=response.dict(),
    )


# Custom exception handler for HTTPExceptions (handles 4XX and 5XX errors)
@app.exception_handler(HTTPException)
async def http_exception_handler(request: Request, exc: HTTPException):
    response = GenericResponse[None](status="error", details=exc.detail)
    return JSONResponse(
        status_code=exc.status_code,
        content=response.dict(),
    )


# Handle all uncaught exceptions
@app.exception_handler(Exception)
async def generic_exception_handler(request: Request, exc: Exception):
    response = GenericResponse[None](status="error", details=str(exc))
    return JSONResponse(
        status_code=500,
        content=response.dict(),
    )


if __name__ == "__main__":
    import uvicorn

    Base.metadata.drop_all(bind=engine)  # TODO: Remove when testing is complete
    Base.metadata.create_all(bind=engine)
    uvicorn.run(app, port=8000)
