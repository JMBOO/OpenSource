import android.content.Context;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class ClickEvent{
	static Context mContext;

	public static void ClickAdapter(Context context)
	{
		mContext = context;
	}
	
	public void Button_ClickE(Button id, final int return_num)
	{
		id.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
		
			}
		});
	}
	
}
