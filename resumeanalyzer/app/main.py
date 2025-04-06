from fastapi import FastAPI
from middlewares import add_middlewares

app = FastAPI()

add_middlewares(app)


@app.post("/api/resume/analyze")
async def getfirstpage(resumeId):
    
    return {"resumeId" : resumeId}
    