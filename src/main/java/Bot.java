import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.meta.exceptions.TelegramApiRequestException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Bot extends TelegramLongPollingBot {
    public static void main(String[] args) {
        ApiContextInitializer.init();
        TelegramBotsApi telegramBotsApi = new TelegramBotsApi();
        try{
            telegramBotsApi.registerBot(new Bot());
        }catch (TelegramApiRequestException e){
            e.printStackTrace();
        }
    }

    @Override
    public void onUpdateReceived(Update update) {
        Model model = new Model();
    Message message = update.getMessage();
    if(message!=null&&message.hasText()){
        switch (message.getText()){
            case "/help":{
                sendMsg(message,"Юзай кнопку");
                break;
            }
            case "Минск":{
                try {
                    sendMsg(message,Weather.getWeather(message.getText(),model));
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
            }
            case "Гомель":{
                try {
                    sendMsg(message,Weather.getWeather(message.getText(),model));
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
            }
            case "Брест":{
                try {
                    sendMsg(message,Weather.getWeather(message.getText(),model));
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
            }
            case "Витебск":{
                try {
                    sendMsg(message,Weather.getWeather(message.getText(),model));
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
            }
            case "Могилёв":{
                try {
                    sendMsg(message,Weather.getWeather(message.getText(),model));
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
            }
            case "Гродно":{
                try {
                    sendMsg(message,Weather.getWeather(message.getText(),model));
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
            }
            default:{
                try {
                    sendMsg(message,Weather.getWeather(message.getText(),model));
                } catch (IOException e) {
                    try {
                        sendMsg(message,Weather.getWeather(message.getText(),model));
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }
                }
                break;
            }

        }
    }
    }

    private void sendMsg(Message message, String s) {

        SendMessage sendMessage = new SendMessage();
        sendMessage.enableMarkdown(true);
        sendMessage.setChatId(message.getChatId().toString());
        sendMessage.setReplyToMessageId(message.getMessageId());
        sendMessage.setText(s);
        try{
            {
                setButton(sendMessage);
            }
            execute(sendMessage);

        }catch (TelegramApiException e){
            e.printStackTrace();
        }
    }

    public void setButton(SendMessage sendMessage){
        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
        sendMessage.setReplyMarkup(replyKeyboardMarkup);
        replyKeyboardMarkup.setSelective(true);
        replyKeyboardMarkup.setResizeKeyboard(true);
        replyKeyboardMarkup.setOneTimeKeyboard(false);

        List<KeyboardRow> keyboardRowList = new ArrayList<>();
        KeyboardRow keyboardFirstRow = new KeyboardRow();
        keyboardFirstRow.add(new KeyboardButton("Гомель"));
        keyboardFirstRow.add(new KeyboardButton("Минск"));
        keyboardFirstRow.add(new KeyboardButton("Могилёв"));
        keyboardFirstRow.add(new KeyboardButton("Гродно"));
        keyboardFirstRow.add(new KeyboardButton("Витебск"));
        keyboardFirstRow.add(new KeyboardButton("Брест"));
        keyboardRowList.add(keyboardFirstRow);
        replyKeyboardMarkup.setKeyboard(keyboardRowList);
    }

    @Override
    public String getBotUsername() {
        return Constants.NAME;
    }

    @Override
    public String getBotToken() {
        return Constants.TOKEN;
    }
}
