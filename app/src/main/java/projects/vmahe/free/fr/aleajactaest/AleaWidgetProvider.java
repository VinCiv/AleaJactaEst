package projects.vmahe.free.fr.aleajactaest;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.widget.RemoteViews;

import java.util.HashMap;
import java.util.Random;

/**
 * Implementation of App Widget functionality.
 */
public class AleaWidgetProvider extends AppWidgetProvider {

	private Random random = new Random();
	private static HashMap<String, Boolean> onOff = new HashMap<String, Boolean>();

	@Override
	public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {

		for (int widgetId : appWidgetIds) {

			// Construct the RemoteViews object
			RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.alea_widget);

			switch (getDiceValue()) {
				case 1:
					if (getOnOff(widgetId))
						views.setImageViewResource(R.id.alea_image, R.drawable.dice1a);
					else
						views.setImageViewResource(R.id.alea_image, R.drawable.dice1b);
					break;

				case 2:
					if (getOnOff(widgetId))
						views.setImageViewResource(R.id.alea_image, R.drawable.dice2a);
					else
						views.setImageViewResource(R.id.alea_image, R.drawable.dice2b);
					break;

				case 3:
					if (getOnOff(widgetId))
						views.setImageViewResource(R.id.alea_image, R.drawable.dice3a);
					else
						views.setImageViewResource(R.id.alea_image, R.drawable.dice3b);
					break;

				case 4:
					if (getOnOff(widgetId))
						views.setImageViewResource(R.id.alea_image, R.drawable.dice4a);
					else
						views.setImageViewResource(R.id.alea_image, R.drawable.dice4b);
					break;

				case 5:
					if (getOnOff(widgetId))
						views.setImageViewResource(R.id.alea_image, R.drawable.dice5a);
					else
						views.setImageViewResource(R.id.alea_image, R.drawable.dice5b);
					break;

				case 6:
					if (getOnOff(widgetId))
						views.setImageViewResource(R.id.alea_image, R.drawable.dice6a);
					else
						views.setImageViewResource(R.id.alea_image, R.drawable.dice6b);
					break;

				default:
					break;
			}
			// Register an onClickListener
			Intent intent = new Intent(context, AleaWidgetProvider.class);

			intent.setAction(AppWidgetManager.ACTION_APPWIDGET_UPDATE);
			intent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_IDS, appWidgetIds);

			PendingIntent pendingIntent = PendingIntent.getBroadcast(context,
					0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
			views.setOnClickPendingIntent(R.id.alea_image, pendingIntent);

			// Instruct the widget manager to update the widget
			appWidgetManager.updateAppWidget(widgetId, views);

		}
	}

	/**
	 * Random value modulo 6 (as the random result is in [0,6[, kind of [0.0, 5.999], we add 1)
	 * @return
	 */
	private int getDiceValue() {
		return random.nextInt(6) + 1;
	}

	/**
	 * The dice must change visually between each throw,
	 * for the user to see it when the result value is the same
	 *
	 * @param widgetId
	 * @return
	 */
	private boolean getOnOff(int widgetId) {

		String widget = Integer.toString(widgetId);

		// first call to the method for the widget
		if (onOff.get(widget) == null) {
			onOff.put(widget, true);
		}

		// switch the current state of the widget
		if (onOff.get(widget)) {
			onOff.put(widget, false);
		} else {
			onOff.put(widget, true);
		}

		// the state to be used for the widget
		return  onOff.get(widget);
	}
}


