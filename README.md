# Starbuck Mobile App on Processing

The idea is to leverage the individual project implementation and re-build the Starbuck Mobile App on processing with animation UI. 

![](https://github.com/nguyensjsu/fa18-202-mystic/blob/master/yinghua_qin/GifMaker_20181120214214887.gif)

Download and install the Processing. https://processing.org/

References:
- Sample code from professor. https://github.com/paulnguyen/cmpe202/tree/master/processing
- Java Programming with Processing from professor. https://slack-files.com/files-pri-safe/T0AMW0A3S-FE1PXQENB/java_programming_with_processing.pdf?c=1542777253-18a17676a94dbe8e972df65fdb4a86d335ebf835
- Processing official website.   https://processing.org/

## Development environment setup instruction (latest version)

We are using visitor design pattern between processing, starbucks.jar and new screens.

All the new screens are under team project folder now. 

1) Here is the source code for implementing the team project: https://github.com/nguyensjsu/fa18-202-mystic/tree/master/5.SourceCode/starbucksTeamSource

2) Here is the latest processing code: https://github.com/nguyensjsu/fa18-202-mystic/tree/master/5.SourceCode/starbucks_processing_v2

3) Here is the two library we use. Please check the readme file here for how to use them.  https://github.com/nguyensjsu/fa18-202-mystic/tree/master/5.SourceCode/starbucksJarLibraries 


## Activity Diagram

![ActivityDiagram](./3.UMLAnalysisModels/ActivityDiagram.png)

## Use Case Diagram

![](./3.UMLAnalysisModels/readme.assets/1543349096953.png)

## System Sequence Diagrams 

### Login and Set Pin

![](./3.UMLAnalysisModels/readme.assets/1543350153375.png)

## Sequence Diagram of Set Pin - Observer Pattern

#### Initial Setup

![1543300184164](./3.UMLAnalysisModels/readme.assets/1543300184164.png)

#### Set Pin Key Input

![1543300295630](./3.UMLAnalysisModels/readme.assets/1543300295630.png)

#### Set Pin 

![1543300420465](./3.UMLAnalysisModels/readme.assets/1543300420465.png)

## Class Diagram

#### Order- Builder Pattern

![](https://user-images.githubusercontent.com/17009702/49313115-230d9100-f49b-11e8-9266-c70c740d3f8a.png)


#### Processing / Base Jar / New Screens - Visitor Pattern

![1543968465578](./README.assets/1543968465578.png)

#### Test Case - Order and Set pin
![](https://github.com/nguyensjsu/fa18-202-mystic/blob/master/README.assets/test-case.png)


### Class Diagram

![](https://github.com/nguyensjsu/fa18-202-mystic/blob/master/README.assets/ClassDiagramOrder.png)


### Sequence Diagram

![](https://github.com/nguyensjsu/fa18-202-mystic/blob/master/README.assets/SequencdDiagramOrder.png)
