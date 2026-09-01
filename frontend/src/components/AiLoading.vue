<template>
  <div class="loading-message">
    <div class="message-label">
      AI
    </div>

    <div class="loading-content">
      <span class="loading-text">답변을 생성하고 있습니다</span>

      <span class="dots">
        <span></span>
        <span></span>
        <span></span>
      </span>
    </div>
  </div>
</template>

<style scoped>
.loading-message {
  display: flex;
  flex-direction: column;
  align-items: flex-start;
  margin-bottom: 24px;
}

.message-label {
  margin-bottom: 6px;
  font-size: 12px;
  font-weight: 600;
  color: #777;
}

.loading-content {
  display: flex;
  align-items: center;
  gap: 4px;
  padding: 12px 16px;
  background: white;
  border: 1px solid #ddd;
  border-radius: 14px;
  color: #777;
  font-size: 14px;
}

.dots {
  display: flex;
  gap: 3px;
  margin-left: 2px;
}

.dots span {
  width: 4px;
  height: 4px;
  border-radius: 50%;
  background: #888;
  animation: blink 1.4s infinite ease-in-out both;
}

.dots span:nth-child(1) {
  animation-delay: -0.32s;
}

.dots span:nth-child(2) {
  animation-delay: -0.16s;
}

@keyframes blink {
  0%,
  80%,
  100% {
    opacity: 0.3;
    transform: translateY(0);
  }

  40% {
    opacity: 1;
    transform: translateY(-3px);
  }
}
</style>