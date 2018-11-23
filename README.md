# CMPE 202 - Team Project - Starbuck Mobile App on Processing

The idea is to leverage the individual project implementation and re-build the Starbuck Mobile App on processing with animation UI. 

![](https://github.com/nguyensjsu/fa18-202-mystic/blob/master/yinghua_qin/GifMaker_20181120214214887.gif)


Download and install the Processing. https://processing.org/

References:
- Sample code from professor. https://github.com/paulnguyen/cmpe202/tree/master/processing
- Java Programming with Processing from professor. https://slack-files.com/files-pri-safe/T0AMW0A3S-FE1PXQENB/java_programming_with_processing.pdf?c=1542777253-18a17676a94dbe8e972df65fdb4a86d335ebf835
- Processing official website.   https://processing.org/

## Journal Week #1

### Login Function (Yinghua Qin)

What I have done this week

- I started from looking at some examples of the Processing. 
- Building UI and annimation on processing seems simple. 

What I plan to do next week

- How to import my individual project Java files to Processing so that I could reuse the source code? 
- Which design patterns I should implement on Login function? The state machine? The observation? Those are implemented on the individual project. 
- Would it be cool to create a QR code scan for login on Processing? It seems there is a QR code scan lib avaiable. 
- Would it be cool to add sound when login fail and a happy sound when login success? 

## Journal Week #2

### Login Function (Yinghua Qin)

What I have done this week

- Imported the individaul project Java files to Processing
- Built a login UI with a pre-set PIN 1234
- Tested the login success case - input 1234 (use mouse click)
- Tested the login fail case - input wrong pin (use mouse click)
- Tested the "x" cancel last input chat
- Created a video recording for the showcase


What I plan to do next week

- Implement the design pattern on Login function
- Link with the starbuck source code for screen flow

## Journal Week #3

### Login Function (Yinghua Qin)

What I have done this week
- I found a wait to use the .jar file in processing. 
- With this, I could put all my personal project java code into a jar file and use it in this team project. 
- Instruction: Put the starbucks.jar into the library folder under Processing software
      processing\modes\java\libraries\starbucks\library\starbucks.jar
- I got all the screens from professor's PDF of personal project
- I worked out the size of the screen and the position mapping as listed on the PDF.
      
      h should same as the 500 in the size function
      
      int headerHeight=60;
      int h=headerHeight+55*8;  //before:480; now: 500
      
      size(320,500);  //480

What I plan to do next week
- Think about some wow factors to add into the project
- Work with the team to get all screens implemented


