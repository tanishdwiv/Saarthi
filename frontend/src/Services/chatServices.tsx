import axiosInstance from "../Interceptor/AxiosInterceptor";

export interface ChatRequest {
  message: string;
}

export interface ChatResponse {
  reply: string;
}

// This shape matches what Gemini’s generateContent endpoint expects
interface GeminiRequest {
  contents: Array<{
    parts: Array<{
      text: string;
    }>;
  }>;
}

export const sendMessage = async (
  payload: ChatRequest
): Promise<ChatResponse> => {
  // 1) Build the Gemini‐compatible JSON
  const geminiPayload: GeminiRequest = {
    contents: [
      {
        parts: [
          {
            text: payload.message,
          },
        ],
      },
    ],
  };

  // 2) Always use POST—no GET calls here
  const { data } = await axiosInstance.post<ChatResponse>(
    "/api/chat",
    geminiPayload
  );

  return data;
};