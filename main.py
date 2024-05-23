from fastapi import FastAPI
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


if __name__ == "__main__":
    import uvicorn

    Base.metadata.drop_all(bind=engine)  # TODO: Remove when testing is complete
    Base.metadata.create_all(bind=engine)
    uvicorn.run(app, port=8000)
