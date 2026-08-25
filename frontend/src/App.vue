<script setup lang="ts">
import { ref } from 'vue'
import Button from 'primevue/button'
import InputText from 'primevue/inputtext'

const file = ref<File | null>(null)
const question = ref('')
const answer = ref('')
const fileName = ref('')
const message = ref('')

const handleFileChange = (event: Event) => {
  const target = event.target as HTMLInputElement

  if (target.files && target.files.length > 0) {
    file.value = target.files[0]
    fileName.value = target.files[0].name
    message.value = ''
  }
}

const uploadFile = () => {
  if (!file.value) {
    message.value = '파일을 선택해주세요.'
    return
  }

  message.value = `${fileName.value} 선택됨`
}

const askQuestion = () => {
  if (!question.value.trim()) {
    answer.value = '질문을 입력해주세요.'
    return
  }

  answer.value = '답변을 불러오는 중...'
}
</script>

<template>
  <main class="container">

    <h1>AI Document Chatbot</h1>

    <section class="card">
      <h2>PDF 문서 업로드</h2>

      <input
        type="file"
        accept=".pdf"
        @change="handleFileChange"
      />

      <p v-if="fileName">
        선택한 파일: {{ fileName }}
      </p>

      <Button
        label="업로드"
        @click="uploadFile"
      />

      <p v-if="message">
        {{ message }}
      </p>
    </section>

    <section class="card">
      <h2>문서에게 질문하기</h2>

      <InputText
        v-model="question"
        placeholder="문서에 대해 질문해주세요."
        class="question-input"
      />

      <Button
        label="질문하기"
        @click="askQuestion"
      />
    </section>

    <section class="card">
      <h2>AI 답변</h2>

      <div class="answer">
        {{ answer || '아직 답변이 없습니다.' }}
      </div>
    </section>

  </main>
</template>

<style scoped>
.container {
  width: 700px;
  margin: 50px auto;
}

h1 {
  margin-bottom: 30px;
}

.card {
  padding: 24px;
  margin-bottom: 20px;
  border: 1px solid #ddd;
  border-radius: 10px;
}

.card h2 {
  margin-top: 0;
}

.question-input {
  width: 70%;
  margin-right: 10px;
}

.answer {
  min-height: 100px;
  padding: 15px;
  background: #f7f7f7;
  border-radius: 8px;
}
</style>