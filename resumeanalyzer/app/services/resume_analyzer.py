import requests
from tika import parser

async def fetchResumePDF(resumeId):
    response = await requests.get("http://localhost:8080/api/resume/getResume?resumeId="+resumeId)
    return response.data

async def fetchPDFdata(resumeId):
    pdfFile = await fetchResumePDF(resumeId=resumeId)
    print("PDF File",pdfFile)
    data = pdfFile
    # data = parser.from_file(pdfFile)
    # print("PDF data : ",data)
    return data
    
async def resume_analyzer(resumeId):
    data = await fetchPDFdata(resumeId=resumeId)
    urlDataJson = 1
    return urlDataJson


    
    
    
    
    
    
    
    
    