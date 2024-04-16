package com.letmecare.housecare.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.letmecare.housecare.R;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

public class RatingFragment extends Fragment {

    private RatingBar ratingBar;
    private Button submitButton;
    private TextView resultTextView;

    public RatingFragment() {
        // Constructor rỗng được yêu cầu
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_rating, container, false);

        ratingBar = view.findViewById(R.id.ratingBar);
        submitButton = view.findViewById(R.id.submitButton);
        resultTextView = view.findViewById(R.id.resultTextView);

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                float rating = ratingBar.getRating();
                sendRatingToServer(rating);
            }
        });

        return view;
    }

    private void sendRatingToServer(float rating) {
        try {
            URL url = new URL("https://example.com/api/send-rating");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
            connection.setDoOutput(true);

            String jsonBody = "{\"rating\": " + rating + "}";

            DataOutputStream outputStream = new DataOutputStream(connection.getOutputStream());
            outputStream.writeBytes(jsonBody);
            outputStream.flush();
            outputStream.close();

            int responseCode = connection.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                // Gửi thành công
                showRatingResult(rating);
            } else {
                // Xử lý lỗi
                Toast.makeText(getContext(), "Gửi đánh giá không thành công", Toast.LENGTH_SHORT).show();
            }

            connection.disconnect();
        } catch (IOException e) {
            e.printStackTrace();
            Toast.makeText(getContext(), "Lỗi kết nối", Toast.LENGTH_SHORT).show();
        }
    }

    private void showRatingResult(float rating) {
        resultTextView.setVisibility(View.VISIBLE);

        if (rating >= 4.0) {
            resultTextView.setText("Đánh giá tốt!");
        } else {
            resultTextView.setText("Đánh giá xấu!");
        }
    }
}