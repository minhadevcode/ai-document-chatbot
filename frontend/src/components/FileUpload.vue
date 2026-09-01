<script setup lang="ts">
import { ref } from 'vue'
import Button from 'primevue/button'
import { uploadPdf } from '../services/api'

const emit = defineEmits<{
  uploaded: [fileName: string]
}>()

const file = ref<File | null>(null)
const fileName = ref('')
const message = ref('')
const uploading = ref(false)
const dragging = ref(false)
const status = ref<'success' | 'error' | ''>('')

const selectFile = (selectedFile: File) => {
  const isPdf = selectedFile.type === 'application/pdf' || selectedFile.name.toLowerCase().endsWith('.pdf')
  if (!isPdf) {
    file.value = null
    fileName.value = ''
    message.value = 'PDF 파일만 업로드할 수 있습니다.'
    status.value = 'error'
    return
  }

  file.value = selectedFile
  fileName.value = selectedFile.name
  message.value = ''
  status.value = ''
}

const handleFileChange = (event: Event) => {
  const target = event.target as HTMLInputElement

  if (target.files && target.files.length > 0) {
    selectFile(target.files[0])
  }
}

const handleDragOver = (event: DragEvent) => {
  event.preventDefault()
  dragging.value = true
}

const handleDragLeave = () => {
  dragging.value = false
}

const handleDrop = (event: DragEvent) => {
  event.preventDefault()
  dragging.value = false

  if (event.dataTransfer?.files && event.dataTransfer.files.length > 0) {
    selectFile(event.dataTransfer.files[0])
  }
}

const uploadFile = async () => {
  if (!file.value) {
    message.value = 'PDF 파일을 선택해주세요.'
    status.value = 'error'
    return
  }

  if (uploading.value) {
    return
  }

  uploading.value = true
  message.value = ''
  status.value = ''

  try {
    const result = await uploadPdf(file.value)

    fileName.value = result.fileName
    message.value = result.message
    status.value = 'success'

    emit('uploaded', result.fileName)
  } catch {
    message.value = '파일 업로드에 실패했습니다.'
    status.value = 'error'
  } finally {
    uploading.value = false
  }
}
</script>

<template>
  <section class="upload-section">
    <div
      class="drop-zone"
      :class="{ dragging }"
      @dragover="handleDragOver"
      @dragleave="handleDragLeave"
      @drop="handleDrop"
    >
      <p class="drop-title">
        PDF 파일을 여기에 드래그하세요
      </p>

      <p class="drop-description">
        또는 아래에서 파일을 선택해주세요.
      </p>

      <label class="file-button">
        파일 선택
        <input
          type="file"
          accept=".pdf,application/pdf"
          hidden
          @change="handleFileChange"
        />
      </label>
    </div>

    <div v-if="fileName" class="file-info">
      <div class="current-file">
        현재 문서: {{ fileName }}
      </div>
    </div>
    
    <div
        v-if="message"
        class="upload-message"
        :class="status"
    >
      {{ message }}
    </div>

    <Button
      class="upload-button"
      :label="uploading ? '업로드 중...' : '업로드'"
      :disabled="uploading || !file"
      @click="uploadFile"
    />
  </section>
</template>

<style scoped>
.upload-section {
  width: 800px;
  margin: 0 auto 20px;
  padding: 20px;
  background: white;
  border: 1px solid #ddd;
  border-radius: 12px;
  box-sizing: border-box;
}

.drop-zone {
  padding: 40px 20px;
  text-align: center;
  border: 2px dashed #ccc;
  border-radius: 10px;
  transition: 0.2s;
}

.drop-zone.dragging {
  border-color: #666;
  background: #f5f5f5;
}

.drop-title {
  margin: 0 0 8px;
  font-size: 16px;
  font-weight: bold;
}

.drop-description {
  margin: 0 0 20px;
  color: #777;
  font-size: 14px;
}

.file-button {
  display: inline-block;
  padding: 10px 18px;
  border: 1px solid #ccc;
  border-radius: 8px;
  cursor: pointer;
  background: white;
}

.file-button:hover {
  background: #f5f5f5;
}

.file-info {
  margin-top: 15px;
}

.current-file {
  font-size: 14px;
  color: #555;
}

.upload-message {
  margin-top: 6px;
  font-size: 13px;
}

.upload-message.success {
  color: #4a7c59;
}

.upload-message.error {
  color: #b45353;
}

.upload-button {
  margin-top: 15px;
}

@media (max-width: 840px) {
  .upload-section {
    width: 100%;
  }
}

@media (max-width: 600px) {
  .upload-section {
    margin-bottom: 16px;
    padding: 14px;
    border-radius: 10px;
  }

  .drop-zone {
    padding: 30px 14px;
  }

  .drop-title {
    font-size: 15px;
  }

  .drop-description {
    font-size: 13px;
  }

  .current-file {
    font-size: 13px;
    overflow-wrap: anywhere;
  }

  .upload-message {
    font-size: 12px;
  }

  .upload-button {
    width: 100%;
  }
}

@media (max-width: 400px) {
  .drop-zone {
    padding: 26px 10px;
  }

  .file-button {
    width: 100%;
    box-sizing: border-box;
  }
}
</style>