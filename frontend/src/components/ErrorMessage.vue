<template>
  <div class="error-message">
    <div class="message-label">
      안내
    </div>

    <div class="error-content">
      <strong>답변을 불러오지 못했습니다.</strong>
      <span>잠시 후 다시 시도해주세요.</span>
    </div>
  </div>
</template>

<style scoped>
.error-message {
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

.error-content {
  display: flex;
  flex-direction: column;
  gap: 4px;
  padding: 12px 16px;
  background: #fff;
  border: 1px solid #ddd;
  border-radius: 14px;
  font-size: 14px;
  line-height: 1.5;
}

.error-content span {
  color: #888;
  font-size: 13px;
}
</style>