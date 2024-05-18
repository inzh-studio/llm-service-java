![Java 17+](https://img.shields.io/badge/Java-17%2B-informational)
![llama.cpp b2927](https://img.shields.io/badge/llama.cpp-%23b2927-informational)
![java-llama.cpp v3.0.1-inzh](https://img.shields.io/badge/java--llama.cpp-v3.0.1--inzh-informational)

# Java LLM service

The main goal of Java LLM service is to propose a packaged solution for execute LLM Service. Release assets contains ready on use multiple inference implementation type.

This project is based from:

 - [llama.cpp](https://github.com/ggerganov/llama.cpp)
 - [java-llama.cpp (fork)](https://github.com/inzh-studio/java-llama.cpp) 

## Quick Start

Download last [release](https://github.com/inzh-studio/llm-service-java/releases), with choosed inference implementation.

- Exemple, use this archive for cpu + gpu inference with vulkan sdk: 

  ```
  inzh-llm-http-service-0.1-vulkan-win-x64.zip
  ```

- Start service with launch script:

  ```shell
  start.bat
  ```

  Start script require JAVA_HOME environnement variable, if missing, use set in command console. For exemple:

  ```shell
  set JAVA_HOME=C:\Program Files\Java\jdk-17
  ```

## Advanced start

- Start with pre open model

  ```shell
  start.bat  --modelPath ./models  --open Meta-Llama-3-8B-Instruct.Q8_0
  ```

## Model list

Bellow, tested GGUF models with ressource consumtion (RAM and VRAM).

| Model  | RAM & VRAM |
| ------------- | ------------- |  
| [Meta-Llama-3-8B-Instruct.Q8_0](https://huggingface.co/QuantFactory/Meta-Llama-3-8B-Instruct-GGUF/resolve/main/Meta-Llama-3-8B-Instruct.Q8_0.gguf)  | ~ 9.6 Go |
| [Meta-Llama-3-8B-Instruct-IQ1_M](https://huggingface.co/bartowski/Meta-Llama-3-8B-Instruct-GGUF/resolve/main/Meta-Llama-3-8B-Instruct-IQ1_M.gguf) | ~ 4 Go |
| [mistral-7b-instruct-v0.2.Q5_K_M](https://huggingface.co/TheBloke/Mistral-7B-Instruct-v0.2-GGUF/resolve/main/mistral-7b-instruct-v0.2.Q5_0.gguf) | ~ 5.3 Go |

## REST Service usage

All request type (except completion) support bulk request. For bulk usage, just use array of request object.

- Completion request for chat:
```shell
curl  http://localhost:18000/v1/chat/completions \
 --json '
{
   "context":"This is a conversation between ${user.name} and ${bot.name}, a friendly chatbot.${bot.name} is helpful, kind, honest, good at writing, and never fails to answer any requests immediately and with precision.",
   "model":"Meta-Llama-3-8B-Instruct.Q8_0",
   "messages":[
      {
         "role":"user",
         "content":"List me the numbers between 1 and 6, from the largest value to the smallest."
      }
   ]
}'
```
```json
{
   "duration":1654,
   "text":"6, 5, 4, 3, 2, 1. That's the list of numbers between 1 and 6, in descending order. Is there anything else I can help you with? "
}
```

- Completion request for chat (EventStream):
```shell
curl -N  http://localhost:18000/v1/chat/completions \
 --json '
{
   "context":"This is a conversation between ${user.name} and ${bot.name}, a friendly chatbot.${bot.name} is helpful, kind, honest, good at writing, and never fails to answer any requests immediately and with precision.",
   "model":"Meta-Llama-3-8B-Instruct.Q8_0",
   "configuration": {
    "stream":true
   },
   "messages":[
      {
         "role":"user",
         "content":"List me the numbers between 1 and 6, from the largest value to the smallest."
      }
   ]
}'
```
```
data: {"duration":390,"text":"6"}

data: {"duration":27,"text":","}

.......

data: {"duration":29,"text":"1"}

data: {"duration":29,"text":"."}

data: {"duration":29,"text":" Would"}

data: {"duration":29,"text":" you"}

data: {"duration":29,"text":" like"}

data: {"duration":29,"text":" to"}

data: {"duration":30,"text":" know"}

data: {"duration":29,"text":" any"}

data: {"duration":30,"text":" other"}

data: {"duration":29,"text":" information"}

data: {"duration":29,"text":"?"}
```

- Embedding request
```shell
curl http://localhost:18000/v1/embeddings \
 --json '
{
   "model":"Meta-Llama-3-8B-Instruct.Q8_0",
   "input":"When i was a child i was a Jedi."
}'
```
```json
{
  "duration": 53,
  "embedding": [
    -0.028669784,
    -0.004733609,
    0.011325254,
    
	.......
	
    0.0013322092,
    0.014150904,
    0.010830845,
    -0.00008983313
  ]
}
```

- Tokenize request
```shell
curl http://localhost:18000/v1/tokenizes \
 --json '
{
   "model":"Meta-Llama-3-8B-Instruct.Q8_0",
   "input":"When i was a child i was a Jedi."
}'
```
```json
{
  "duration": 1,
  "tokens": [
    4599,
    602,
    574,
    264,
    1716,
    602,
    574,
    264,
    41495,
    13
  ]
}
```

- Resolve request value from token
```shell
curl http://localhost:18000/v1/resolves \
 --json '
{
   "model":"Meta-Llama-3-8B-Instruct.Q8_0",
   "tokens":[
      4599,
      602,
      574,
      264,
      1716,
      602,
      574,
      264,
      41495,
      13
   ]
}'
```
```json
{
  "duration": 1,
  "text": "When i was a child i was a Jedi."
}
 ```