package com.example.overseasshopping;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.overseasshopping.Model.Chat;
import com.example.overseasshopping.Model.User;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ChatActivity extends AppCompatActivity {

    private String receiverId;
    private String receiverName;
    private String senderId;

    private TextView userName, userLastSeen;
    private ImageView userImage;

    private ImageButton SendMessageButton;
    private EditText MessageInputText;

    private Toolbar ChatToolBar;
    private DatabaseHelper db;

    private final List<Chat> mChatList = new ArrayList<>();
    private LinearLayoutManager linearLayoutManager;
    private ChatAdapter chatAdapter;
    private RecyclerView userChatsList;
    private String saveCurrentDate;
    private String saveCurrentTime;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        db = new DatabaseHelper(this);
        User user = new User ("1","1","1","1","1","1",null,0,0);
        db.addUser(user);
        String user1 = user.getUsername();
        senderId = String.valueOf(db.getUser(user1).getUserNo());

        receiverId = getIntent().getExtras().get("visit_userNo").toString();
        receiverName = getIntent().getExtras().get("visit_username").toString();

        userName.setText(receiverName);

        SendMessageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                //SendMessage();
            }
        });

    }

    private void IntializeControllers()
    {
        ChatToolBar = (Toolbar) findViewById(R.id.chat_toolbar);
        setSupportActionBar(ChatToolBar);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setDisplayShowCustomEnabled(true);

        LayoutInflater layoutInflater = (LayoutInflater) this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View actionBarView = layoutInflater.inflate(R.layout.custom_chat_bar, null);
        actionBar.setCustomView(actionBarView);

        userName = (TextView) findViewById(R.id.custom_profile_name);
        userLastSeen = (TextView) findViewById(R.id.custom_user_last_seen);
        userImage = (ImageView) findViewById(R.id.custom_profile_image);

        SendMessageButton = (ImageButton) findViewById(R.id.send_message_btn);
        MessageInputText = (EditText) findViewById(R.id.input_message);

        chatAdapter = new ChatAdapter(mChatList);
        userChatsList = (RecyclerView) findViewById(R.id.private_messages_list_of_users);
        linearLayoutManager = new LinearLayoutManager(this);
        userChatsList.setLayoutManager(linearLayoutManager);
        userChatsList.setAdapter(chatAdapter);


        Calendar calendar = Calendar.getInstance();

        SimpleDateFormat currentDate = new SimpleDateFormat("MMM dd, yyyy");
        saveCurrentDate = currentDate.format(calendar.getTime());

        SimpleDateFormat currentTime = new SimpleDateFormat("hh:mm a");
        saveCurrentTime = currentTime.format(calendar.getTime());
    }

//    private void SendMessage() {
//
//        String messageText = MessageInputText.getText().toString();
//
//        if (TextUtils.isEmpty(messageText))
//        {
//            Toast.makeText(this, "first write your message...", Toast.LENGTH_SHORT).show();
//        }
//        else
//        {
//            String messageSenderRef = "Messages/" + senderId + "/" + receiverId;
//            String messageReceiverRef = "Messages/" + receiverId + "/" + senderId;
//
//            Map messageTextBody = new HashMap();
//            messageTextBody.put("message", messageText);
//            messageTextBody.put("type", "text");
//            messageTextBody.put("from", senderId);
//            messageTextBody.put("to", receiverId);
//            messageTextBody.put("time", saveCurrentTime);
//            messageTextBody.put("date", saveCurrentDate);
//
//            Map messageBodyDetails = new HashMap();
//            messageBodyDetails.put(messageSenderRef + "/" + messagePushID, messageTextBody);
//            messageBodyDetails.put( messageReceiverRef + "/" + messagePushID, messageTextBody);
//
//        }
//    }

}
