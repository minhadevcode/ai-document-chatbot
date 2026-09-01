<script setup lang="ts">
import { ref } from 'vue'
import Button from 'primevue/button'

defineProps<{
  loading: boolean
}>()

const emit = defineEmits<{
  send: [question: string]
}>()

const question = ref('')

const sendQuestion = () => {
  const value = question.value.trim()

  if (!value) {
    return
  }

  emit('send', value)
  question.value = ''
}

const handleKeydown = (event: KeyboardEvent) => {
  // Enter = 전송
  // Shift + Enter = 줄바꿈
  if (event.key === 'Enter' && !event.shiftKey) {
    event.preventDefault()
    sendQuestion()
  }
}
</script>

<template>
  <div class="input-area">
    <textarea
      v-model="question"
      class="question-input"
      placeholder="문서에 대해 질문해주세요."
      rows="1"
      :disabled="loading"
      @keydown="handleKeydown"
    />

    <Button
      class="send-button"
      :label="loading ? '답변 중...' : '전송'"
      :disabled="loading || !question.trim()"
      @click="sendQuestion"
    />
  </div>
</template>

<style scoped>
.input-area {
  display: flex;
  align-items: center;
  gap: 10px;
  padding: 16px;
  border-top: 1px solid #ddd;
  background: white;
  box-sizing: border-box;
}

.question-input {
  flex: 1;
  min-width: 0;
  min-height: 42px;
  max-height: 120px;
  padding: 10px 12px;
  resize: vertical;
  border: 1px solid #ccc;
  border-radius: 8px;
  outline: none;
  font-family: inherit;
  font-size: 14px;
  line-height: 1.5;
  box-sizing: border-box;
}

.question-input:focus {
  border-color: #888;
}

.question-input:disabled {
  background: #f5f5f5;
}

.send-button {
  flex-shrink: 0;
}

@media (max-width: 600px) {
  .input-area {
    gap: 8px;
    padding: 10px;
  }

  .question-input {
    min-height: 40px;
    padding: 9px 10px;
    font-size: 13px;
  }

  .send-button {
    min-width: 64px;
  }
}

@media (max-width: 400px) {
  .input-area {
    gap: 6px;
    padding: 8px;
  }

  .question-input {
    font-size: 12px;
  }

  .send-button {
    min-width: 58px;
  }
}
</style>