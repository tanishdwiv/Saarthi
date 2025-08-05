// import { useState, useRef, useEffect } from "react";
// import { sendMessage, ChatResponse } from "../Services/chatServices";

// export default function ChatWidget() {
//   const [open, setOpen] = useState(false);
//   const [history, setHistory] = useState<ChatResponse[]>([]);
//   const [input, setInput] = useState("");
//   const bottomRef = useRef<HTMLDivElement>(null);

//   const toggleOpen = () => {
//     setOpen(o => {
//       if (!o) {
//         // Simulate typing indicator
//         setHistory(h => [...h, { reply: "..." }]);
//         setTimeout(() => {
//           setHistory(h => [
//             ...h.slice(0, -1), // Remove typing
//             {
//               reply: "👋 Hi there! I’m Saarthi, your career companion. Ask me anything—from job tips to tech questions!",
//             },
//           ]);
//         }, 800); // Delay for realism
//       }
//       return !o;
//     });
//   };

//   const handleSubmit = async (e: React.FormEvent) => {
//     e.preventDefault();
//     if (!input.trim()) return;

//     const userMsg = { reply: input };
//     setHistory(h => [...h, userMsg]);
//     setInput("");

//     try {
//       const response = await sendMessage({ message: userMsg.reply });
//       setHistory(h => [...h, response]);
//     } catch (err) {
//       console.error(err);
//       setHistory(h => [...h, { reply: "⚠️ Failed to get response. Please try again." }]);
//     }
//   };

//   useEffect(() => {
//     bottomRef.current?.scrollIntoView({ behavior: "smooth" });
//   }, [history]);

//   return (
//     <div className="fixed bottom-6 right-6 z-50 flex flex-col items-end">
//       {open && (
//         <div className="w-96 max-h-[32rem] bg-white shadow-2xl rounded-xl flex flex-col overflow-hidden border border-gray-200">
//           {/* Header */}
//           <div className="px-4 py-3 bg-indigo-600 text-white flex justify-between items-center">
//             <span className="font-semibold text-lg">Saarthi Chat</span>
//             <button onClick={toggleOpen} className="text-white text-xl hover:text-gray-300">✕</button>
//           </div>

//           {/* Chat History */}
//           <div className="flex-1 px-4 py-3 overflow-y-auto space-y-4 bg-gray-50">
//             {history.map((msg, idx) => {
//               const isUser = idx % 2 === 0;
//               return (
//                 <div key={idx} className={`flex ${isUser ? "justify-end" : "justify-start"}`}>
//                   <div className={`max-w-[80%] px-4 py-2 rounded-lg shadow-sm text-sm ${isUser ? "bg-indigo-100 text-indigo-900" : "bg-white text-gray-800 border"}`}>
//                     {msg.reply}
//                   </div>
//                 </div>
//               );
//             })}
//             <div ref={bottomRef} />
//           </div>

//           {/* Input */}
//           <form onSubmit={handleSubmit} className="flex border-t bg-white">
//             <input
//               className="flex-1 px-4 py-3 text-sm focus:outline-none"
//               value={input}
//               onChange={e => setInput(e.target.value)}
//               placeholder="Type your message..."
//             />
//             <button type="submit" className="px-5 bg-indigo-600 text-white hover:bg-indigo-700 transition">
//               Send
//             </button>
//           </form>
//         </div>
//       )}

//       {/* Floating Button */}
//       <button
//         onClick={toggleOpen}
//         className="w-16 h-16 rounded-full bg-indigo-600 text-white text-2xl flex items-center justify-center shadow-xl hover:bg-indigo-700 transition"
//       >
//         💬
//       </button>
//     </div>
//   );
// }

import { useState, useRef, useEffect } from "react";
import { sendMessage, ChatResponse } from "../Services/chatServices";

export default function ChatWidget() {
  const [open, setOpen] = useState(false);
  const [history, setHistory] = useState<ChatResponse[]>([]);
  const [input, setInput] = useState("");
  const bottomRef = useRef<HTMLDivElement>(null);

  const toggleOpen = () => {
    setOpen(o => {
      if (!o) {
        setHistory(h => [...h, { reply: "..." }]);
        setTimeout(() => {
          setHistory(h => [
            ...h.slice(0, -1),
            {
              reply: "👋 Hi there! I’m Saarthi, your career companion. Ask me anything—from job tips to tech questions!",
            },
          ]);
        }, 800);
      }
      return !o;
    });
  };

  const handleSubmit = async (e: React.FormEvent) => {
    e.preventDefault();
    if (!input.trim()) return;

    const userMsg = { reply: input };
    setHistory(h => [...h, userMsg]);
    setInput("");

    try {
      const response = await sendMessage({ message: userMsg.reply });
      setHistory(h => [...h, response]);
    } catch (err) {
      console.error(err);
      setHistory(h => [...h, { reply: "⚠️ Failed to get response. Please try again." }]);
    }
  };

  useEffect(() => {
    bottomRef.current?.scrollIntoView({ behavior: "smooth" });
  }, [history]);

  return (
    <div className="fixed bottom-6 right-6 z-50 flex flex-col items-end">
      {open && (
        <div className="w-96 max-h-[32rem] bg-white shadow-2xl rounded-xl flex flex-col overflow-hidden border border-gray-200">
          {/* Header */}
          <div className="px-4 py-3 bg-yellow-500 text-white flex justify-between items-center">
            <span className="font-semibold text-lg">Saarthi Chat</span>
            <button onClick={toggleOpen} className="text-white text-xl hover:text-gray-300">✕</button>
          </div>

          {/* Chat History */}
          <div className="flex-1 px-4 py-3 overflow-y-auto space-y-4 bg-gray-50">
            {history.map((msg, idx) => {
              const isUser = idx % 2 === 0;
              return (
                <div key={idx} className={`flex ${isUser ? "justify-end" : "justify-start"}`}>
                  <div className={`max-w-[80%] px-4 py-2 rounded-lg shadow-sm text-sm ${isUser ? "bg-yellow-100 text-yellow-900" : "bg-white text-gray-800 border"}`}>
                    {msg.reply}
                  </div>
                </div>
              );
            })}
            <div ref={bottomRef} />
          </div>

          {/* Input */}
          <form onSubmit={handleSubmit} className="flex border-t bg-white">
            <input
              className="flex-1 px-4 py-3 text-sm focus:outline-none"
              value={input}
              onChange={e => setInput(e.target.value)}
              placeholder="Type your message..."
            />
            <button type="submit" className="px-5 bg-yellow-500 text-white hover:bg-yellow-600 transition">
              Send
            </button>
          </form>
        </div>
      )}

      {/* Floating Button */}
      <button
        onClick={toggleOpen}
        className="w-16 h-16 rounded-full bg-yellow-500 text-white text-2xl flex items-center justify-center shadow-xl hover:bg-yellow-600 transition"
      >
        💬
      </button>
    </div>
  );
}