package com.letmecare.housecare;

import opennlp.tools.sentdetect.SentenceDetectorME;
import opennlp.tools.sentdetect.SentenceModel;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class ReviewAnalyzer {
    private SentenceDetectorME sentenceDetector;

    public ReviewAnalyzer() {
        try {
            // Load sentence detection model from file
            InputStream modelIn = new FileInputStream("assets/opennlp-en-ud-ewt-sentence-1.0-1.9.3.bin");
            SentenceModel model = new SentenceModel(modelIn);
            sentenceDetector = new SentenceDetectorME(model);
            modelIn.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public String[] detectSentences(String review) {
        // Phát hiện các câu trong đánh giá từ khách hàng
        String[] sentences = sentenceDetector.sentDetect(review);
        return sentences;
    }
}

