from fastapi import FastAPI

app = FastAPI()

@app.get("/")
def return1():
    return {"name" : "vignesh"}


@app.post("/analyzeresume")
def analyzeresume():
    return 