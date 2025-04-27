from fastapi import APIRouter

router = APIRouter()

@router.post("/generate-script")
async def generate_script(pdf_file, full_document):
    pass

