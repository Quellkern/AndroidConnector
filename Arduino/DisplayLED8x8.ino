#include <MD_Parola.h>
#include <MD_MAX72xx.h>

#include <SPI.h>
#include <Wire.h>

// Define the number of devices we have in the chain and the hardware interface
// NOTE: These pin numbers will probably not work with your hardware and may
// need to be adapted
#define HARDWARE_TYPE MD_MAX72XX::FC16_HW
#define MAX_DEVICES 4 // Define the number of displays connected
#define CLK_PIN    52 // CLK or SCK
#define DATA_PIN   51 // DATA or MOSI
#define CS_PIN     53 // CS or SS
#define PAUSE_TIME  0
#define MAX_MESG   20

const uint16_t WAIT_TIME = 1000;
const byte numChars = 128;
char receivedChars[numChars];   // an array to store the received data
char displaychar[numChars];
char checkChars[numChars] = {"\n"};
// int count = 0;      // an array to store the display data
boolean newData = false;
int arrSize;
// Hardware SPI connection
//MD_Parola P = MD_Parola(HARDWARE_TYPE, DATA_PIN, CLK_PIN, CS_PIN, MAX_DEVICES);
//MD_MAX72XX mx = MD_MAX72XX(HARDWARE_TYPE, CS_PIN, MAX_DEVICES);
MD_Parola P = MD_Parola(HARDWARE_TYPE, CS_PIN, MAX_DEVICES);
void recvWithEndMarker() {
    static byte ndx = 0;
    char endMarker = '\n';
    char rc;
    while (Serial3.available() > 0 && newData == false) {
      rc = Serial3.read();
      if (rc != endMarker) {
          receivedChars[ndx] = rc;
          ndx++;
          // count = ndx;
          if (ndx >= numChars) {
              ndx = numChars - 1;
          }
          arrSize = ndx;         
      }
      else 
      {
          receivedChars[ndx-1] = '\0';
          ndx = 0;
          newData = true;
      }
  }
}

void showNewData() {
    if (newData == true) {
      strcpy(displaychar,receivedChars);
      //  displaychar[count-1] = NULL;
       Serial.print("displaychar=");
       Serial.print(displaychar);
       Serial.println("-----");  
    newData = false;
    }
}


void setup(void)    
{
  Serial.begin(9600);
  Serial3.begin(9600);
  P.begin();
  P.setIntensity(1);
  P.setTextAlignment(PA_CENTER);
  P.print("MEGA");
  delay(1000);
  P.displayClear();
}

void loop() {
  recvWithEndMarker();
  showNewData();
  if(arrSize > 6){
    if (P.displayAnimate()){
        if(strcmp(checkChars,displaychar) != 0){
        P.displayClear();
        strcpy(checkChars,displaychar);
      }
      P.displayScroll(displaychar,PA_CENTER,PA_SCROLL_LEFT,150);
    }
  }
  else
    if(P.displayAnimate())
      P.print(displaychar);
}
