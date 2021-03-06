package getlo.tlqkf.example.com.timer;


import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.TextView;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private CountDownTimer timer;
    private static int b = 35640000, a = 100;
   private int h = 00, m = 00, s = 00, mS = 00, d = 0, change = 0;
    TextView time;
    Button stRco, pause, clear;
    private ListView list;
    private ScrollView scroll;
    private final ArrayList<String> item = new ArrayList();
    private ArrayAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        clear = (Button) findViewById(R.id.clear);
        clear.setOnClickListener(this);
        pause = (Button) findViewById(R.id.pause);
        pause.setOnClickListener(this);
        stRco = (Button) findViewById(R.id.stRco);
        stRco.setOnClickListener(this);
        time = (TextView) findViewById(R.id.timer);
        time.setOnClickListener(this);
        list = (ListView) findViewById(R.id.list);
        scroll = (ScrollView) findViewById(R.id.scroll);
        adapter = new ArrayAdapter(this,R.layout.support_simple_spinner_dropdown_item,item);
        list.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                scroll.requestDisallowInterceptTouchEvent(true);
                return false;
            }
        });
        list.setAdapter(adapter);
    }

    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.stRco:
                if (change == 0) {
                    stRco.setText("기록");
                    timer();
                    timer.start();
                    change = 1;
                } else if (change == 1) {
                    if(h == 0)
                        item.add( m + ":" + s + "." + mS);
                    else
                    item.add("" + h + ":" + m + ":" + s + "." + mS);
                    adapter.notifyDataSetChanged();
                }
                break;
            case R.id.pause:
                timer.cancel();
                change = 0;
                stRco.setText("시작");
                break;
            case R.id.clear:
                timer.cancel();
                change = 0;
                h = 0;
                s = 0;
                mS = 0;
                m = 0;

                item.add( m + ":" + s + "." + mS);
                stRco.setText("시작");
                break;
        }
    }

    public void timer() {
        timer = new CountDownTimer(b, a) {
            @Override
            public void onTick(long millisUntilFinished) {
                mS++;
                if (mS == 10) {
                    s++;
                    mS = 0;
                }
                if (s == 60) {
                    m++;
                    s = 0;
                }
                if (m == 60) {
                    h++;
                    m = 0;
                }
                if (h == 24)
                    d++;
                if(h == 0)
                    time.setText( m + ":" + s + "." + mS);
                else
                    time.setText("" + h + ":" + m + ":" + s + "." + mS);
            }

            @Override
            public void onFinish() {
            }
        };
    }
}
