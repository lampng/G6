package Personal_development.g6.customizable;

import android.widget.Button;

public interface MinusPlusClickListener {
//    void onClick(int position, Button btn_minus, Button btn_plus, TextView tv_quantity, TextView tv_price, TextView total_price, ImageView img_drink, TextView tv_name);
    void onMinusClick(int position, Button btn_minus);
    void onPlusClick(int position, Button btn_plus);
}
