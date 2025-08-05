# 🧠 Saarthi – AI-Powered Job Portal

**Saarthi** is a full-stack job portal application designed to streamline the hiring process for both job seekers and recruiters. What sets Saarthi apart is its integrated **AI-powered assistant** — **Saarthi Chat** — which helps users navigate the platform, apply for jobs, and receive personalized career guidance.

---

## 📌 Key Features

### 👨‍💼 Job Portal
- User authentication and role-based access (Job Seeker & Employer)
- Job creation, management, and application tracking
- Resume upload and profile management
- Employer dashboard for managing applicants
- Search and filter functionality for job listings

### 🤖 Saarthi Chat – AI Assistant
- Context-aware chatbot integrated into the portal
- Provides job recommendations and platform navigation help
- Answers FAQs regarding hiring and applications
- Assists with resume building and interview tips
- (Optional) Multilingual support and voice input (planned)

---

## ⚙️ Tech Stack

| Layer         | Technology                            |
|---------------|----------------------------------------|
| Frontend      | React.js, Tailwind CSS                 |
| Backend       | Spring Boot (Java)                     |
| Database      | MySQL                                  |
| AI Chatbot    | OpenAI API (ChatGPT), Google GenAI SDK |
| Authentication| Spring Security                        |
| Deployment    | Localhost (Development Phase)          |

---

## 🚀 Getting Started

### 1. Clone the Repository
```bash
git clone https://github.com/tanishdwiv/Saarthi.git
cd Saarthi
```

### 2. Backend Setup (Spring Boot)
```bash
cd backend
./mvnw clean install
```

#### ➤ Configure `application.properties`:
```properties
spring.datasource.url=jdbc:mysql://localhost:3306/saarthi_db
spring.datasource.username=root
spring.datasource.password=yourpassword

openai.api.key=your_openai_key
```

#### ➤ Run the Spring Boot Server:
```bash
./mvnw spring-boot:run
```

### 3. Frontend Setup (React)
```bash
cd ../frontend
npm install
npm start
```

### 4. Access the App
- Open your browser and go to `http://localhost:3000`
- Register as a **Job Seeker** or **Employer**
- Start using **Saarthi Chat** via the chatbot widget

---

## 💬 How Saarthi Chat Works

1. User types a query in the chat interface.
2. Message is sent to the backend via REST API (`/api/chat`).
3. Backend interacts with OpenAI or Google GenAI to generate a contextual response.
4. The chatbot sends back helpful suggestions or resources.

> Saarthi Chat can recommend jobs, help with resume prep, provide interview tips, and answer queries about using the platform.

---

## 📁 Project Structure

```
Saarthi/
├── backend/
│   └── src/main/java/com/saarthi/...
│   └── application.properties
├── frontend/
│   ├── src/
│   │   └── components/
│   │       └── SaarthiChat.js
├── README.md
```

---

## ✅ To-Do / Roadmap

- [x] Job portal with authentication
- [x] AI chatbot with OpenAI/GenAI integration
- [x] Employer and candidate dashboards
- [ ] Resume parsing and matching via NLP
- [ ] Voice support using Whisper
- [ ] Multilingual chatbot support (e.g. Hindi + English)
- [ ] Dockerization for deployment

---

## 📦 Deployment (Coming Soon)

> Deployment can be done using **Docker**, **Heroku**, or **AWS EC2**. A `Dockerfile` and `docker-compose.yml` will be added in future iterations.

---

## 🤝 Contributing

We welcome contributions to improve Saarthi!

1. Fork the repository
2. Create your branch: `git checkout -b feature/your-feature`
3. Commit your changes: `git commit -m 'Add your feature'`
4. Push to your branch: `git push origin feature/your-feature`
5. Open a Pull Request

---

## 🛡 License

This project is licensed under the **MIT License**. See the `LICENSE` file for details.

---

## 🙏 Acknowledgments

- [OpenAI](https://openai.com/)
- [Google GenAI](https://ai.google.dev/)
- [Spring Boot](https://spring.io/)
- [React](https://reactjs.org/)
- [MySQL](https://www.mysql.com/)
- [Tailwind CSS](https://tailwindcss.com/)

---

> 🚧 *This project is actively under development. Feedback and suggestions are highly appreciated!*
