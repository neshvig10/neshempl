from fastapi import FastAPI
from middlewares import add_middlewares
from router import routes

app = FastAPI()

app.include_router(routes.router)
add_middlewares(app)
    