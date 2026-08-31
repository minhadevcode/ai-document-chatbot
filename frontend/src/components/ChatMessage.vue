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
  margin-bottom: 24px;
}

.message.user {
  align-items: flex-end;
}

.message.ai {
  align-items: flex-start;
}

.message-label {
  margin-bottom: 6px;
  font-size: 12px;
  font-weight: 600;
  color: #777;
}

.message-content {
  max-width: 70%;
  padding: 12px 16px;
  border-radius: 14px;
  line-height: 1.6;
  white-space: pre-wrap;
  word-break: break-word;
}

.user .message-content {
  background: #e9e9e9;
}

.ai .message-content {
  background: white;
  border: 1px solid #ddd;
}
</style>