<script setup lang="ts">
defineProps<{
  role: 'user' | 'ai'
  content: string
}>()
</script>

<template>
  <div class="message" :class="role">
    <div class="message-label">
      {{ role === 'user' ? '나' : 'AI' }}
    </div>

    <div class="message-content">
      {{ content }}
    </div>
  </div>
</template>

<style scoped>
.message {
  display: flex;
  flex-direction: column;
  margin-bottom: 20px;
}

.message.user {
  align-items: flex-end;
}

.message.ai {
  align-items: flex-start;
}

.message-label {
  margin-bottom: 5px;
  padding: 0 4px;
  font-size: 12px;
  font-weight: 600;
  color: #777;
}

.message-content {
  max-width: 70%;
  padding: 12px 16px;
  border-radius: 14px;
  line-height: 1.6;
  font-size: 14px;
  white-space: pre-wrap;
  overflow-wrap: anywhere;
}

/* 사용자 메시지 */
.user .message-content {
  background: #e9e9e9;
  color: #333;
  border-bottom-right-radius: 4px;
}

/* AI 메시지 */
.ai .message-content {
  background: white;
  color: #333;
  border: 1px solid #ddd;
  border-bottom-left-radius: 4px;
}

/* 모바일 */
@media (max-width: 600px) {
  .message-content {
    max-width: 85%;
  }
}
</style>