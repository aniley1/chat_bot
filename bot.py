import tkinter as tk
import re

# Define predefined rules and responses
rules = {
    r"(?i)(hello|hi|hey)": "Hello! How can I assist you?",
    r"(?i)how are you": "I'm just a chatbot, but I'm here to help!",
    r"(?i)weather": "I'm sorry, I can't provide real-time weather information.",
    r"(?i)time": "I don't have access to the current time right now.",
    r"(?i)goodbye|bye": "Goodbye! Have a great day!",
    r"calculate (.*)": None,  # Special rule for calculations
}

def calculate_expression(expression):
    try:
        result = eval(expression)
        return f"The result is: {result}"
    except Exception as e:
        return f"Sorry, I couldn't calculate that. Error: {str(e)}"

def get_bot_response(user_query):
    for pattern, response in rules.items():
        if re.search(pattern, user_query):
            if response is None:
                calculation_match = re.match(pattern, user_query)
                if calculation_match:
                    expression = calculation_match.group(1)
                    return calculate_expression(expression)
            return response

    return "I'm not sure how to respond to that. Please ask me something else."

def send_message():
    user_query = user_input.get()
    if not user_query:
        return

    bot_response = get_bot_response(user_query.lower())
    chat_log.insert(tk.END, f"User: {user_query}\n")
    chat_log.insert(tk.END, f"Chatbot: {bot_response}\n")
    chat_log.insert(tk.END, "\n")
    user_input.delete(0, tk.END)

# Create a GUI window
window = tk.Tk()
window.title("Chatbot GUI")

# Create a text widget for chat logs
chat_log = tk.Text(window, height=10, width=40)
chat_log.pack()

# Create an entry widget for user input
user_input = tk.Entry(window, width=40)
user_input.pack()

# Create a "Send" button
send_button = tk.Button(window, text="Send", command=send_message)
send_button.pack()

# Start the GUI main loop
window.mainloop()
