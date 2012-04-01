package teekoon.ss.pakkaus;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.TextView;


/**
 * Starting Strength is a workout calculator for Mark Rippetoe's Starting Strength program.
 * @author Taneli Kärkkäinen
 */
public class StartingStrengthActivity extends Activity {
		//3 TV objects for second layout to show all the workouts.
	TextView work1TV, work2TV, work3TV;
		//5 TV objects for deload layout
	TextView deload1, deload2, deload3, deload4, deload5;
		//3 TV objects for progress layout to show starting weight, highest weight and gained weight. 
	TextView smProgressStart, smProgressHighest, smProgressGain;
		//int values for counting next/previous workout weights.
	int getDL, getOHP, getSQT, getBNCH, getCLEAN;
		//int used in all the algorithms when counting next/previous workouts and deload weights.
	int workOutcounter = 0;
		//starting weights after Ready button is pressed on main layout(aka first page)
	int startSquat, startOHP,startDL,startClean,startBench;
		//highest weight ever needed for progress layout.
	int highest = 0;
		//table of 2 string values to show which workout's turn is it.
	String[] workoutString = new String[]{"(A)", "(B)"};
		//int values to get all the values from seekBars on deload layout.
	int seekBarProgressSquat, seekBarProgressClean, seekBarProgressOHP, seekBarProgressDL, seekBarProgressBench;
		//working weights that get's printed on second layout
	double weightSetSQT, weightSetCLEAN, weightSetDL, weightSetOHP, weightSetBNCH;
		//ET objects needed at the start when getting the starting weights on main layout
	EditText deadliftET, benchET, ohpET, powercleanET, squatET;
		//Context object needed for dialog boxes.
	Context cont = this;
	
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        mainButtonListeners();
        
    }
    /**Button listener for Android's "Back" button. This method makes sure that the app isn't terminated.**/
    @Override	
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            moveTaskToBack(true);
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
	
    protected void onStart(Bundle savedInstanceState){
    	super.onStart();
    }
    
    protected void onRestart(Bundle savedInstanceState){
    	super.onRestart();

    }

    protected void onResume(Bundle savedInstanceState){
    	super.onResume();
    }

    protected void onPause(Bundle savedInstanceState){
    	super.onPause();
    }

    protected void onStop(Bundle savedInstanceState){
    	super.onStop();
    }

    protected void onDestroy(Bundle savedInstanceState){
    	super.onDestroy();
    }
	
	
 
    
  	 /**Get buttons for second layout. This method needs to be called after changing layout so ActionListeners are activated**/
    private void listenerStates(){
        Button resetbutton = (Button) findViewById(R.id.resetID);
    	resetbutton.setOnClickListener(clickListenerReset);
    	Button nextButton = (Button) findViewById(R.id.nextButton);
    	nextButton.setOnClickListener(clickListenerNext);
    	Button previousButton = (Button) findViewById(R.id.button3);
    	previousButton.setOnClickListener(clickListenerPrevious);
    	Button deloadButton = (Button) findViewById(R.id.deloadB);
    	deloadButton.setOnClickListener(clickListenerDeload);
    	Button progressButton = (Button) findViewById(R.id.button2);
    	progressButton.setOnClickListener(clickListenerProgress);
    }
    	/**Listener for progress layout. It prints the highest weight, starting weight and gains**/
    View.OnClickListener clickListenerProgress = new View.OnClickListener() {
		
		public void onClick(View v) {
			
			
			weightSetSQT = getSQT + 2.5*workOutcounter;
			weightSetOHP = getOHP + (1.25*workOutcounter-1.25);
			weightSetCLEAN = getCLEAN + (1.25*workOutcounter-1.25);
			weightSetBNCH = getBNCH + 1.25*workOutcounter;
			weightSetDL = getDL + 2.5*workOutcounter;
			
			setContentView(R.layout.progress);
			Button readyProgressB = (Button) findViewById(R.id.pReadyButton);
			readyProgressB.setOnClickListener(clicklListenerProgressReadyButton);
			
			smProgressStart = (TextView) findViewById(R.id.smSquat1);
			smProgressHighest = (TextView) findViewById(R.id.smSquat2);
			smProgressGain = (TextView) findViewById(R.id.smSquat3);
			smProgressStart.setText("Starting weight: " + startSquat);
			smProgressHighest.setText("Highest weight: " + (startSquat+(2.5*highest)));
			smProgressGain.setText("Gains: " + ((startSquat+(2.5*highest))-startSquat));
			
			smProgressStart = (TextView) findViewById(R.id.smDL1);
			smProgressHighest = (TextView) findViewById(R.id.smDL2);
			smProgressGain = (TextView) findViewById(R.id.smDL3);
			smProgressStart.setText("Starting weight: " + startDL);
			smProgressHighest.setText("Highest weight: " + (startDL+(2.5*highest)));
			smProgressGain.setText("Gains: " + ((startDL+(2.5*highest))-startDL));
			
			smProgressStart = (TextView) findViewById(R.id.smBench1);
			smProgressHighest = (TextView) findViewById(R.id.smBench2);
			smProgressGain = (TextView) findViewById(R.id.smBench3);
			smProgressStart.setText("Starting weight: " + startBench);
			smProgressHighest.setText("Highest weight: " + (startBench+(1.25*highest)));
			smProgressGain.setText("Gains: " + ((startBench+(1.25*highest))-startBench));
			
			smProgressStart = (TextView) findViewById(R.id.smOHP1);
			smProgressHighest = (TextView) findViewById(R.id.smOHP2);
			smProgressGain = (TextView) findViewById(R.id.smOHP3);
			smProgressStart.setText("Starting weight: " + startOHP);
			smProgressHighest.setText("Highest weight: " + (startOHP+(1.25*highest)));
			smProgressGain.setText("Gains: " + ((startOHP+(1.25*highest))-startOHP));
			
			smProgressStart = (TextView) findViewById(R.id.smClean1);
			smProgressHighest = (TextView) findViewById(R.id.smClean2);
			smProgressGain = (TextView) findViewById(R.id.smClean3);
			smProgressStart.setText("Starting weight: " + startClean);
			smProgressHighest.setText("Highest weight: " + (startClean+(1.25*highest)));
			smProgressGain.setText("Gains: " + ((startClean+(1.25*highest))-startClean));
			
		}
	};
		/**Listener for Ready button on Progress layout. It returns user to the second layout page.**/
	View.OnClickListener clicklListenerProgressReadyButton = new View.OnClickListener() {
		
		public void onClick(View v) {
			setContentView(R.layout.second);
			updateWorkouts();
			listenerStates();
			
		}
	};
    	/**Listener for Ready button on deload layout. This gets all the values from deload layouts seekBars and sends user back to the second layout**/
    View.OnClickListener clickListenerReadyDeload = new View.OnClickListener() {
		
		public void onClick(View v) {
			setContentView(R.layout.second);
			getBNCH-= seekBarProgressBench;
			getCLEAN-= seekBarProgressClean;
			getDL-= seekBarProgressDL;
			getOHP-= seekBarProgressOHP;
			getSQT-= seekBarProgressSquat;
			listenerStates();
			updateWorkouts();
			
		}
	};
    
    
    
    	/**Listener for Deload layout and Ready button. This method gets all the values from deload layouts seekBars**/
    View.OnClickListener clickListenerDeload = new View.OnClickListener() {
		
		public void onClick(View v) {										
			setContentView(R.layout.deloadout);
			  /**Bar tracking and listeners**/
		    SeekBar squatSeekBar = (SeekBar) findViewById(R.id.seekSquat);
		    SeekBar DLSeekBar = (SeekBar) findViewById(R.id.seekDL);
		    SeekBar benchSeekBar = (SeekBar) findViewById(R.id.seekBench);
		    SeekBar OHPSeekBar = (SeekBar) findViewById(R.id.seekOHP);
		    SeekBar cleanSeekBar = (SeekBar) findViewById(R.id.seekClean);
		    Button readyDeload = (Button) findViewById(R.id.deloadReadyButton);
			readyDeload.setOnClickListener(clickListenerReadyDeload);

		    squatSeekBar.setOnSeekBarChangeListener(new OnSeekBarChangeListener()						//start of seekBar listeners
		    {
		       public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser)
		       {
		            seekBar.setMax(15);
		            seekBarProgressSquat = seekBar.getProgress();
		            deload1 = (TextView) findViewById(R.id.deloadTV1);
		            deload1.setText("Squat: " + seekBarProgressSquat);
		            
		       }

		      public void onStartTrackingTouch(SeekBar seekBar) {}

		      public void onStopTrackingTouch(SeekBar seekBar) {}
		    });
		    
		    DLSeekBar.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {
				
				public void onStopTrackingTouch(SeekBar seekBar) {
					
					
				}
				
				public void onStartTrackingTouch(SeekBar seekBar) {
					
					
				}
				
				public void onProgressChanged(SeekBar seekBar, int progress,
						boolean fromUser) {
					seekBar.setMax(15);
					seekBarProgressDL = seekBar.getProgress();
					deload2 = (TextView) findViewById(R.id.deloadTV2);
		            deload2.setText("Deadlift: " + seekBarProgressDL);
					
				}
			});
		    
		    benchSeekBar.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {
				
				public void onStopTrackingTouch(SeekBar seekBar) {
					
					
				}
				
				public void onStartTrackingTouch(SeekBar seekBar) {
					
					
				}
				
				public void onProgressChanged(SeekBar seekBar, int progress,
						boolean fromUser) {
					seekBar.setMax(15);
					seekBarProgressBench = seekBar.getProgress();
					deload3 = (TextView) findViewById(R.id.deloadTV3);
		            deload3.setText("Bench: " + seekBarProgressBench);
					
				}
			});
		    
		    OHPSeekBar.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {
				
				public void onStopTrackingTouch(SeekBar seekBar) {
					
					
				}
				
				public void onStartTrackingTouch(SeekBar seekBar) {
					
					
				}
				
				public void onProgressChanged(SeekBar seekBar, int progress,
						boolean fromUser) {
					seekBar.setMax(15);
					seekBarProgressOHP = seekBar.getProgress();
					deload4 = (TextView) findViewById(R.id.deloadTV4);
		            deload4.setText("OHP: " + seekBarProgressOHP);
				}
			});
		    
		    cleanSeekBar.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {
				
				public void onStopTrackingTouch(SeekBar seekBar) {
					
					
				}
				
				public void onStartTrackingTouch(SeekBar seekBar) {
					
					
				}
				
				public void onProgressChanged(SeekBar seekBar, int progress,
						boolean fromUser) {
					seekBar.setMax(15);
					seekBarProgressClean = seekBar.getProgress();
					deload5 = (TextView) findViewById(R.id.deloadTV5);
		            deload5.setText("Powerclean: " + seekBarProgressClean);
																									//end of seekBar listeners
				}
			});
			
		}
	};

    	/**Listener for previous button on second layout. This method brings user back to the previous workout**/
    View.OnClickListener clickListenerPrevious = new View.OnClickListener() {
    	public void onClick(View v) {
    		if(workOutcounter>=1){
    			workOutcounter--;
    		
    		updateWorkouts();
    		
    		}
    	}
    	};
    	/**Listener for main layout's Ready button**/
    public void mainButtonListeners(){
        /**Button listener for Readybutton**/
        Button readybutton = (Button) findViewById(R.id.button1);   
        readybutton.setOnClickListener(clickListenerReady);
    }
    
    	/**Actionlistener to reset button. This method resets all the weights and brings user back to the main layout.**/
    View.OnClickListener clickListenerReset = new View.OnClickListener() {
        public void onClick(View v) {
        	
            new AlertDialog.Builder(cont)
            .setIcon(android.R.drawable.ic_dialog_alert)
            .setTitle("Reset")
            .setMessage("Are you sure you want to reset?")
            .setPositiveButton("Yes", new DialogInterface.OnClickListener() {

               
                public void onClick(DialogInterface dialog, int which) {
                	setContentView(R.layout.main);
                	workOutcounter = 0;
                	mainButtonListeners();
                	setSecondContentsToZero(true);
                      
                }

            })
            .setNegativeButton("No", null)
            .show();
            

        }
    };
    
	/**Actionlistener to NEXT button**/
    View.OnClickListener clickListenerNext = new View.OnClickListener() {
    	public void onClick(View v) {
    		workOutcounter++;    		
    		updateWorkouts();
    		
    	}
    };
    
	/**Actionlistener to ready button on main layout. This method stores all the starting weights and sends user to the second layout page**/
    View.OnClickListener clickListenerReady = new View.OnClickListener() {
    	public void onClick(View v) {
    		 		
        	

        	try{
        		setSecondsContents();
        		
        	setContentView(R.layout.second);
        	updateWorkouts();
        	
        		
        	 listenerStates();
        	
        	}
        	catch(Exception e){
            	  new AlertDialog.Builder(cont)
                  .setIcon(android.R.drawable.ic_dialog_alert)
                  .setTitle("Missing information.")
                  .setMessage("Please fill in all the starting weights")
                  .setPositiveButton("OK", new DialogInterface.OnClickListener() {

                      public void onClick(DialogInterface dialog, int which) {

                             
                      }

                  }).show();
        	}
              
    	}

    	

    };
    
    	/**Updates and calculates the next/previous workouts and prints them on the second layout page**/
	public void updateWorkouts() {
    	TextView workoutTV = (TextView) findViewById(R.id.textView1);
    	
    	work1TV = (TextView) findViewById(R.id.work1);
    	work2TV = (TextView) findViewById(R.id.work2);
    	work3TV = (TextView) findViewById(R.id.work3);
    	
		weightSetSQT = getSQT + 2.5*workOutcounter;
		weightSetOHP = getOHP + (1.25*workOutcounter-1.25);
		weightSetCLEAN = getCLEAN + (1.25*workOutcounter-1.25);
		weightSetBNCH = getBNCH + 1.25*workOutcounter;
		weightSetDL = getDL + 2.5*workOutcounter;
		
		if(workOutcounter>highest)
			highest = workOutcounter; 
		
			if(workOutcounter%2==0){
				workoutTV.setText("Workout "+ workoutString[0] + " " + (workOutcounter+1));
			work1TV.setText("Squat 3x5 " + weightSetSQT + " kg");
    		work2TV.setText("Bench 3x5 " + weightSetBNCH + " kg");
    		work3TV.setText("Deadlift 1x5 " + weightSetDL + " kg");
			}
			
			if(workOutcounter%2!=0){
				workoutTV.setText("Workout "+ workoutString[1] + " " + (workOutcounter+1));
			work1TV.setText("Squat 3x5 " + weightSetSQT + " kg");
    		work2TV.setText("OHPress 3x5 " + weightSetOHP + " kg");
    		work3TV.setText("PClean 3x5 " + weightSetCLEAN + " kg");
			}
			

		
	}
    
    	/**Get all values after pressing readybutton**/
	private void setSecondsContents() {
    	//getting all the values from the workouts
    	squatET = (EditText) findViewById(R.id.editText1);
    	powercleanET = (EditText) findViewById(R.id.editText2);
    	benchET = (EditText) findViewById(R.id.editText3);
    	deadliftET = (EditText) findViewById(R.id.editText4);
    	ohpET = (EditText) findViewById(R.id.editText5);
		
	getSQT = Integer.parseInt(squatET.getText().toString().trim());
	getCLEAN = Integer.parseInt(powercleanET.getText().toString().trim());
	getDL = Integer.parseInt(deadliftET.getText().toString().trim());
	getOHP = Integer.parseInt(ohpET.getText().toString().trim());
	getBNCH = Integer.parseInt(benchET.getText().toString().trim());
	
	startSquat = Integer.parseInt(squatET.getText().toString().trim());
	startClean = Integer.parseInt(powercleanET.getText().toString().trim());
	startDL = Integer.parseInt(deadliftET.getText().toString().trim());
	startOHP = Integer.parseInt(ohpET.getText().toString().trim());
	startBench = Integer.parseInt(benchET.getText().toString().trim());
		
	}
	
		/**Set all values to zero. This method resets the app and brings user to the main layout
		 * @param boolean value to confirm the reset (for future updates)		 
		 */
	private void setSecondContentsToZero(boolean confirm){
		if(confirm){
		workOutcounter = 0;
		getSQT = 0;
		getCLEAN = 0;
		getDL = 0;
		getOHP = 0;
		getBNCH = 0;
		}
	}
}