from fastapi import APIRouter

router = APIRouter()

@router.post("generate-script")
async def generate_script():
    print("호출 성공")