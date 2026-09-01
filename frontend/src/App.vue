<script setup lang="ts">
import { nextTick, ref, watch } from 'vue'
import { askQuestion as requestQuestion } from './services/api'
import FileUpload from './components/FileUpload.vue'
import ChatMessage from './components/ChatMessage.vue'
import AiLoading from './components/AiLoading.vue'
import ErrorMessage from './components/ErrorMessage.vue'
import ChatInput from './components/ChatInput.vue'

interface Message {
  role: 'user' | 'ai'
  content: string
}

const fileName = ref('')
const messages = ref<Message[]>([])
const messagesContainer = ref<HTMLElement | null>(null)

watch(
  messages,
  async () => {
    await nextTick()

    if (messagesContainer.value) {
      messagesContainer.value.scrollTop =
        messagesContainer.value.scrollHeight
    }
  },
  { deep: true }
)

const loading = ref(false)
const error = ref(false)

const handleUploaded = (name: string) => {
  fileName.value = name
  messages.value = []
}

const sendQuestion = async (question: string) => {
  messages.value.push({
    role: 'user',
    content: question
  })

  loading.value = true
  error.value = false

  try {
    const result = await requestQuestion(question)

    messages.value.push({
      role: 'ai',
      content: result.answer
    })
  } catch {
    error.value = true
  } finally {
    loading.value = false
  }
}
</script>

<template>
  <main class="app">

    <header class="header">
      <h1>AI Document Chatbot</h1>
      <p>PDF 문서를 업로드하고 질문해보세요.</p>
    </header>

    <FileUpload @uploaded="handleUploaded" />

    <section class="chat-container">

      <div ref="messagesContainer" class="messages">

        <div 
          v-if="messages.length === 0"
          class="empty-message">
          <div class="empty-content">
            <h2>문서에 대해 질문해보세요</h2>

            <p v-if="!fileName">
              먼저 PDF 파일을 업로드해주세요.
            </p>

            <p v-else>
              <strong>{{ fileName }}</strong>에 대해 궁금한 내용을 질문해보세요.
            </p>

            <div class="question-examples">
              <span>질문 예시</span>

              <p>• 이 문서의 주요 내용은 무엇인가요?</p>
              <p>• 문서에서 코드 리뷰에 대해 설명해주세요.</p>
              <p>• 문서에 없는 내용도 알려줄 수 있나요?</p>
            </div>
          </div>
        </div>

        <ChatMessage
          v-for="(message, index) in messages"
          :key="index"
          :role="message.role"
          :content="message.content"
        />

        <AiLoading v-if="loading" />
        
        <ErrorMessage v-if="error" />

      </div>

      <ChatInput
        :loading="loading"
        @send="sendQuestion"
      />

    </section>

  </main>
</template>

<style scoped>
.app {
  min-height: 100vh;
  padding: 40px 20px;
  background: #f5f6f8;
  box-sizing: border-box;
}

.header {
  width: 800px;
  margin: 0 auto 24px;
}

.header h1 {
  margin: 0 0 8px;
  font-size: 28px;
}

.header p {
  margin: 0;
  color: #666;
}

.chat-container {
  width: 800px;
  height: 600px;
  margin: 20px auto 0;
  display: flex;
  flex-direction: column;
  background: white;
  border: 1px solid #ddd;
  border-radius: 12px;
  overflow: hidden;
}

.messages {
  flex: 1;
  overflow-y: auto;
  padding: 24px;
  background: #fafafa;
}

.empty-message {
  height: 100%;
  display: flex;
  align-items: center;
  justify-content: center;
  color: #999;
  text-align: center;
}

.empty-content {
  max-width: 500px;
}

.empty-content h2 {
  margin: 0 0 10px;
  color: #333;
  font-size: 20px;
}

.empty-content p {
  margin: 6px 0;
}

.question-examples {
  margin-top: 24px;
  padding: 16px 20px;
  background: white;
  border: 1px solid #e5e5e5;
  border-radius: 10px;
  text-align: left;
}

.question-examples span {
  font-size: 13px;
  font-weight: bold;
  color: #666;
}

.question-examples p {
  font-size: 13px;
  color: #777;
}

/* 태블릿 */
@media (max-width: 840px) {
  .app {
    padding: 30px 16px;
  }

  .header,
  .chat-container {
    width: 100%;
  }

  .chat-container {
    height: 70vh;
  }
}

/* 모바일 */
@media (max-width: 600px) {
  .app {
    padding: 20px 12px;
  }

  .header {
    margin-bottom: 16px;
  }

  .header h1 {
    font-size: 22px;
  }

  .header p {
    font-size: 13px;
  }

  .chat-container {
    margin-top: 16px;
    height: calc(100vh - 230px);
    min-height: 450px;
    border-radius: 10px;
  }

  .messages {
    padding: 16px 12px;
  }

  .empty-content {
    max-width: 90%;
  }

  .empty-content h2 {
    font-size: 18px;
  }

  .question-examples {
    margin-top: 20px;
    padding: 14px 16px;
  }
}

/* 작은 모바일 */
@media (max-width: 400px) {
  .app {
    padding: 16px 8px;
  }

  .header h1 {
    font-size: 20px;
  }

  .chat-container {
    height: calc(100vh - 210px);
    min-height: 400px;
  }

  .messages {
    padding: 14px 10px;
  }
}
</style>