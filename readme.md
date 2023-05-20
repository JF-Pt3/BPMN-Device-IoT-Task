# BPMN Device Task
#### Nowadays, there are several platforms, such as Google Cloud IoT and others that allows the integration and configuration of IoT devices. Through the databases of these platforms, it is possible to manage this kind of devices, however there is a need to program the IoT devices in advance. As indicated before, business model processes in industrial environment, can facilitate communication between actors in the process, optimizing support. However, this typology of tools originally does not have mechanisms to program devices, such as microcontrollers, and it is always necessary to program them through strategies that are external to BPMN modeling. The main focus of this program is to present a procedure embedded in a business model process, namely a Device Task that allows the parameterization and subsequent automatic generation of device code to be inserted into a microcontroller. Specifically, the Model Driven Architecture (MDA) paradigm was used in order to achieve the goal, because allows maintaining a separation between the technological implementation aspects and the system operation aspects.

#### The project is based on the source code of the open-source project Imixs BPMN modeller that was made by the [author](https://www.imixs.org/), adding a new capability to the BPMN. It has been validated through the IMIXS project's Engine [author](https://www.imixs.org/). Essentially a mechanism architected through the Model Driven Architecture paradigm was added, embodied through the Atlas Transformation Language. Through the figure below the following is observed:
![alt text](https://github.com/JF-Pt3/BPMN-Device-IoT-Task/blob/master/m2m.jpg)


#### We move from a device model that is the Device task in BPMN ( which is described in XML according to the corresponding Ecore meta model, in MDA terms it corresponds to the PIM level) to another device model that already situates in a technological level (PSM layer of MDA). Then through the Queries ATL concept the code extraction is done, see figure below:

![alt text](https://github.com/JF-Pt3/BPMN-Device-IoT-Task/blob/master/m2t.jpg)

#### So there are two moments to take into account: Design Time and Run Time. In Design Time, the architect or engineer draws the entire BPMN diagram representing the processes that represent the phenomenon or mechanism. Here the new BPMN task (Device Task) allows you to parameterize a device and automatically generate its executable code. On the other hand, in Run time, the execution of the BPMN process and the interaction between the programmed device and the engine (with data exchange) is verified.

#### The next figure shows the system architecture, where the positioning of each technological module in relation to the "Design Time" and "Run Time" moments is displayed:

![alt text](https://github.com/JF-Pt3/BPMN-Device-IoT-Task/blob/master/system_archit.jpg)

#### Short video demonstrating the solution:

[Device Task](https://www.youtube.com/watch?v=037_fr-PbxQ)

