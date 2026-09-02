import axios from 'axios'

const api = axios.create({
    baseURL: 'http://localhost:8080',
})

export const uploadPdf = async (file: File) => {
    const formData = new FormData()
    formData.append('file', file)

    const response = await api.post('/api/upload', formData)
    return response.data
}

export const askQuestion = async (
    question: string,
    history: { role: 'user' | 'ai'; content: string }[]
) => {
    const response = await api.post('/api/chat', {
        question,
        history
    })

    return response.data
}