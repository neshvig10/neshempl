from fastapi import APIRouter
from services.resume_analyzer import resume_analyzer

router = APIRouter()


@router.post("/api/resume/analyze")
async def analyseresume(resumeId):
    return await resume_analyzer(resumeId=resumeId)



