from langchain_google_genai import ChatGoogleGenerativeAI

class LLMBase:
    def __init__(self, prompt_path, output_parser, model_params):
        self.prompt_path = prompt_path
        self.output_parser = output_parser
        self.llm = ChatGoogleGenerativeAI(**model_params)
    
    def _make_chain(self):
        pass

    def _get_template(self):
        with open(self.prompt_path, "r") as f:
            return f.read()

    def _set_prompt(self):
        pass

    def invoke(self):
        pass