package ru.omapp.driver.fragment;

import android.annotation.SuppressLint;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListPopupWindow;

import ru.omapp.driver.R;

public class FragmentRegistration extends Fragment {

    private EditText colorsEditText;
    private String[] colorsStrings;
    public View view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_registration, null);

        colorsStrings = getResources().getStringArray(R.array.car_colors);
        colorsEditText = (EditText) view.findViewById(R.id.edt_car_color);
        setListIcon(colorsEditText, colorsStrings);

        return view;

    }

    private void setListIcon(EditText editText, String[] list) {
        if (list.length <= 1) {
            editText.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0);
            editText.setOnTouchListener(null);
        } else {
            editText.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_arrow_down, 0);
            editText.setOnTouchListener(new TouchListener());
        }
    }

    private class TouchListener implements View.OnTouchListener {
        private ListPopupWindow lpw;

        @SuppressLint("ClickableViewAccessibility")
        @Override
        public boolean onTouch(final View v, MotionEvent event) {
            final int DRAWABLE_RIGHT = 2;
            final String[] list;

            if (v == colorsEditText) {
                list = colorsStrings;
            } else {
                list = null;
            }

            lpw = new ListPopupWindow(view.getContext());
            lpw.setAdapter(new ArrayAdapter<String>(view.getContext(), android.R.layout.simple_list_item_1, list));
            lpw.setAnchorView(v);
            lpw.setModal(true);
            lpw.setOnItemClickListener(new AdapterView.OnItemClickListener() {

                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    String item = list[position];
                    ((EditText) v).setText(item);
                    v.setPressed(false);
                    lpw.dismiss();
                }
            });

            if (event.getAction() == MotionEvent.ACTION_UP) {
                if (event.getX() >= (v.getWidth() - ((EditText) v)
                        .getCompoundDrawables()[DRAWABLE_RIGHT].getBounds().width())) {
                    lpw.show();
                    return true;
                }
            }
            return false;
        }
    }
}
