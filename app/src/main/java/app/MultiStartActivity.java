package app;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import io.victoralbertos.app.R;
import rx_activity_result.RxActivityResult;

public class MultiStartActivity extends AppCompatActivity {
    private TextView firstResult;
    private TextView secondResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.multi_start);

        View startTwoForResult = findViewById(R.id.start_two_for_result);
        firstResult = (TextView) findViewById(R.id.first_result);
        secondResult = (TextView) findViewById(R.id.second_result);

        startTwoForResult.setOnClickListener(v -> {
            startFirstForResult();
            startSecondForResult();
        });
    }

    private void startFirstForResult() {
        RxActivityResult.on(this)
                .startIntent(new Intent(this, FirstActivity.class))
                .subscribe(result -> {
                    firstResult.setText(result.data().getStringExtra(FirstActivity.EXTRA));
                });
    }

    private void startSecondForResult() {
        RxActivityResult.on(this)
                .startIntent(new Intent(this, SecondActivity.class))
                .subscribe(result -> {
                    secondResult.setText(result.data().getStringExtra(SecondActivity.EXTRA));
                });
    }
}
