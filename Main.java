//This is a COVID-19 vaccine distribution class that distributes vaccines to all of California equally and in a fair manner
//Merge Test
import java.util.Scanner;

class Main {
  public static void main(String[] args) {

    County[] counties = new County[10];

    //populationOfCountyNotVaccinatedIn, numHealthCareWorkersOfCountyIn, numNonHealthWorkersUnableToSocialDistanceIn, numPeopleSixtyOrHigherIn numPeopleFiftyFiveOrHigherWithConditionIn, numPeopleFiftyToSixtyIn, numPeopleEighteenToFiftyFiveWithConditionIn, numPeopleEighteenToFiftyIn

    counties[0] = new County(0, 765495, 3059926, 1299277, 2171396, 1178985, 1177974, 6613748); //Los Angeles County
    counties[1] = new County(0, 251853, 1007414, 676022, 714225, 404232, 387380, 1452199); // San Diego County
    counties[2] = new County(0, 240610, 962443, 502411, 682342, 412889, 370087, 1587216); // Orange County
    counties[3] = new County(0, 183147, 732589, 505165, 519382, 296825, 281701, 1281238); // Riverside County
    counties[4] = new County(0, 163217, 652870, 337304, 462864, 259289, 251047, 913343); // San Bernardino County
    counties[5] = new County(0, 146390, 585561, 376397, 415144, 247072, 225165, 844437); // Santa Clara County
    counties[6] = new County(0, 125829, 503318, 336146, 356836, 210958, 193540, 744688); // Alameda County
    counties[7] = new County(0, 115789, 463155, 314507, 328362, 192827, 178097, 645799); // Sacramento County
    counties[8] = new County(0, 86753, 347013, 258584, 246021, 161577, 133437, 447041); // Contra Costa County
    counties[9] = new County(0, 74774, 299095, 175750, 212049, 107283, 115011, 406519); // Fresno County

    int totalPopulationAllCounties = 0;

    // Totalling all population for each county
    // This assumes that the total population is the sum of all the groups
    for (int j = 0; j < counties.length; j++)
    {
      counties[j].populationOfCountyNotVaccinated = counties[j].numHealthCareWorkersOfCounty + counties[j].numNonHealthWorkersUnableToSocialDistance + counties[j].numPeopleSixtyOrHigher + counties[j].numPeopleFiftyFiveOrHigherWithCondition + counties[j].numPeopleFiftyToSixty + counties[j].numPeopleEighteenToFiftyFiveWithCondition + counties[j].numPeopleEighteenToFifty;

      totalPopulationAllCounties += counties[j].populationOfCountyNotVaccinated;

    }

    //creats a covid object from the CovidScreener class and pass to it "counties" array as the parameter
    CovidScreener covid = new CovidScreener(counties);

    Scanner jjVaccineRead;
    Scanner pfizerModernaVaccineRead;

    //initialize the variables of the number of johnson & johnson and pfizer/moderna vaccines to one in order to enter the while loop: they will then get updated when the user inputes a number

    int jjVaccines = 1;
    int pfizerModernaVaccines = 1;
    int vaccines = 0;
    covid.totalPopulationNotVaccinated = 1;

    //Create a while a loop in order to continue asking the user to input vaccines until they either input 0 for both vaccine type or until everyone is vaccinated
    while ((jjVaccines > 0 || pfizerModernaVaccines > 0) && covid.totalPopulationNotVaccinated > 0)
    {
      //asks for input of johnson & johnson vaccines
      jjVaccineRead = new Scanner(System.in);
      System.out.println("Enter Number of J&J Vaccines: ");

      jjVaccines = jjVaccineRead.nextInt();  // Read user input
      System.out.println("Total Number of People to Vaccinate with J&J Vaccines: " + jjVaccines);  // Output user input

      //asks for input of pfizer/moderna vaccines
      pfizerModernaVaccineRead = new Scanner(System.in);  // Create a Scanner object
      System.out.println("Enter Number of Pfizer and Moderna Vaccines: ");

      pfizerModernaVaccines = pfizerModernaVaccineRead.nextInt();  // Read user input
      System.out.println("Total Number of People to Vaccinate with Pfizer and Moderna Vaccines: " + pfizerModernaVaccines/2);  // Output user input

      vaccines = jjVaccines + pfizerModernaVaccines/2;

      //Start distributting vaccines. Call the group distribution method in the covid object and pass to it as parameters the total number of vaccines available and which group to process.
      //The for loop process one group at a time.
      //g can have the value 1 through 6 and represents the group number which is inputted in the GroupDistribution method as the parameter value of grpToVaccinate
      for(int g = 1; g <= 6; g++) {
        if (vaccines > 0) {
          vaccines = covid.GroupDistribution(vaccines, g);
          System.out.println("Group " + g + " Remaining Vaccines: " + vaccines);
          System.out.println(" ");
        }
      }

    }
  }
}

//Declaring class variables to be used to correspond to the data in the “counties” County array
class County {
  public int populationOfCountyNotVaccinated;
  public int numHealthCareWorkersOfCounty;
  public int numNonHealthWorkersUnableToSocialDistance;

  public int numPeopleSixtyOrHigher;
  public int numPeopleFiftyFiveOrHigherWithCondition;
  
  public int numPeopleFiftyToSixty;
  public int numPeopleEighteenToFiftyFiveWithCondition;
  public int numPeopleEighteenToFifty;
  //collection of data where each index corresponds to a different group

//creating a constructor to initialize the declared variables for each object correponding to the array data
public County (int populationOfCountyNotVaccinatedIn, int numHealthCareWorkersOfCountyIn, int numNonHealthWorkersUnableToSocialDistanceIn, int numPeopleSixtyOrHigherIn, int numPeopleFiftyFiveOrHigherWithConditionIn, int numPeopleFiftyToSixtyIn, int numPeopleEighteenToFiftyFiveWithConditionIn, int numPeopleEighteenToFiftyIn) {
    
    populationOfCountyNotVaccinated = populationOfCountyNotVaccinatedIn;
    numHealthCareWorkersOfCounty = numHealthCareWorkersOfCountyIn;
    numNonHealthWorkersUnableToSocialDistance = numNonHealthWorkersUnableToSocialDistanceIn;
    numPeopleSixtyOrHigher = numPeopleSixtyOrHigherIn;
    numPeopleFiftyFiveOrHigherWithCondition = numPeopleFiftyFiveOrHigherWithConditionIn;
    numPeopleFiftyToSixty = numPeopleFiftyToSixtyIn;
    numPeopleEighteenToFiftyFiveWithCondition = numPeopleEighteenToFiftyFiveWithConditionIn;
    numPeopleEighteenToFifty = numPeopleEighteenToFiftyIn;
  }

}

//created a class to process all the vaccine information
class CovidScreener{
  public County counties[];
  public int totalPopulationNotVaccinated = 0;

  //constructor to receive counties array information
  public CovidScreener(County[] countiesIn){
    this.counties = countiesIn;
  }

  //Distribute vaccines. Parameters represent the total number of vaccines available and which group to process.
  public int GroupDistribution(int grpVaccines, int grpToVaccinate) {
      //grpVaccines is the total number of vaccines we have to distribute to the all six groups
      //grpToVaccinate is a number 1 through 6 representing the six groups
      int vaccinated = 0;
      int vaccinated1 = 0;
      int vaccinated2 = 0;
      int availableVaccines = 0;
      int totalVaccinated = 0;
      int vaccinePerCounty[] = new int[counties.length];
      String grpToPrint = "";
      int totalToVaccinate = 0;
      int j = 0;

      for(int i = 0; i < counties.length; i++) {
        switch(grpToVaccinate)
        {
          case 1: // Health Care Workers
            totalToVaccinate += counties[i].numHealthCareWorkersOfCounty;
            grpToPrint = "Group 1 Total Health Care Workers";
            break;
          case 2: // Non Health Workers Unable To Social Distance
            totalToVaccinate += counties[i].numNonHealthWorkersUnableToSocialDistance;
            grpToPrint = "Group 2 Total Exposed to Health Care";
            break;
          case 3: // People Sixty Or Higher + People Fifty Five Or Higher With Condition
            totalToVaccinate += counties[i].numPeopleSixtyOrHigher + counties[i].numPeopleFiftyFiveOrHigherWithCondition;
            grpToPrint = "Group 3 Total Sixty or Higher and Fifty Five or Higher with Condition";
            break;
          case 4: // People Fifty To Sixty
            totalToVaccinate += counties[i].numPeopleFiftyToSixty;
            grpToPrint = "Group 4 Total Fifty to Sixty";
            break;
          case 5: // People Eighteen To Fifty Five With Condition
            totalToVaccinate += counties[i].numPeopleEighteenToFiftyFiveWithCondition;
            grpToPrint = "Group 5 Total Eighteen to Fifty Five with Condition";
            break;
          case 6: // People Eighteen To Fifty
            totalToVaccinate += counties[i].numPeopleEighteenToFifty;
            grpToPrint = "Group 6 Total Eighteen to Fifty";
            break;
        }
      }

      //Printing the number of people in each group that need to be vaccinated
      System.out.println(grpToPrint + " Needed to Vaccinate: " + totalToVaccinate);
      System.out.println(" ");      

      //Check if the vaccines inputted is greater than the number needed to vaccinate for a specific group, if so, than we have enough to distribute to all groups in all counties.
      if(grpVaccines >= totalToVaccinate)
      {
        for(int i = 0; i < counties.length; i++) {

          switch(grpToVaccinate)
          {
            case 1: //Distribute to Health Care Workers
              vaccinePerCounty[i] = counties[i].numHealthCareWorkersOfCounty;
              counties[i].populationOfCountyNotVaccinated -= counties[i].numHealthCareWorkersOfCounty;
              counties[i].numHealthCareWorkersOfCounty = 0;
              break;
            case 2: //Distribute to Non Health Workers Unable To Social Distance
              vaccinePerCounty[i] = counties[i].numNonHealthWorkersUnableToSocialDistance;
              counties[i].populationOfCountyNotVaccinated -= counties[i].numNonHealthWorkersUnableToSocialDistance;
              counties[i].numNonHealthWorkersUnableToSocialDistance = 0;
              break;
            case 3: //Distribute to People Sixty Or Higher + People Fifty Five Or Higher With Condition
              vaccinePerCounty[i] = counties[i].numPeopleSixtyOrHigher + counties[i].numPeopleFiftyFiveOrHigherWithCondition;
              counties[i].populationOfCountyNotVaccinated -= counties[i].numPeopleSixtyOrHigher + counties[i].numPeopleFiftyFiveOrHigherWithCondition;
              counties[i].numPeopleSixtyOrHigher = 0;
              counties[i].numPeopleFiftyFiveOrHigherWithCondition = 0;
              break;
            case 4: //Distribute to People Fifty To Sixty
              vaccinePerCounty[i] = counties[i].numPeopleFiftyToSixty;
              counties[i].populationOfCountyNotVaccinated -= counties[i].numPeopleFiftyToSixty;
              counties[i].numPeopleFiftyToSixty = 0;
              break;
            case 5: //Distribute to People Eighteen To Fifty Five With Condition
              vaccinePerCounty[i] = counties[i].numPeopleEighteenToFiftyFiveWithCondition;
              counties[i].populationOfCountyNotVaccinated -= counties[i].numPeopleEighteenToFiftyFiveWithCondition;
              counties[i].numPeopleEighteenToFiftyFiveWithCondition = 0;
              break;
            case 6: //Distribute to People Eighteen To Fifty
              vaccinePerCounty[i] = counties[i].numPeopleEighteenToFifty;
              counties[i].populationOfCountyNotVaccinated -= counties[i].numPeopleEighteenToFifty;
              counties[i].numPeopleEighteenToFifty = 0;
              break;
          }
          //Updates the total people vaccinated
          totalVaccinated += vaccinePerCounty[i];
        }
        availableVaccines = grpVaccines - totalToVaccinate;
      } 

      //If we don't have enough for all the gourps in all counties, then distribute the vaccines based on proportions.
      else
      {
        //Calculate the proportion value based on the total vaccines availabe divided by the total people to vaccinate
        double proportion = (double)grpVaccines / totalToVaccinate;

        for(int i = 0; i < counties.length; i++) {
          
          //Use the switch function to process one group at a time.
          //Using switch instead of if is faster.
          switch(grpToVaccinate)
          {
            case 1: // Health Care Workers
              vaccinated =  (int)(proportion * counties[i].numHealthCareWorkersOfCounty);
              counties[i].numHealthCareWorkersOfCounty -= vaccinated;
              break;
            case 2: // Non Health Workers Unable To Social Distance
              vaccinated =  (int)(proportion * counties[i].numNonHealthWorkersUnableToSocialDistance);
              counties[i].numNonHealthWorkersUnableToSocialDistance -= vaccinated;
              break;
            case 3: // People Sixty Or Higher + People Fifty Five Or Higher With Condition
              vaccinated1 =  (int)(proportion * (counties[i].numPeopleSixtyOrHigher));
              vaccinated2 = (int)(proportion * (counties[i].numPeopleFiftyFiveOrHigherWithCondition)); //changed

              vaccinated = (vaccinated1 + vaccinated2);

              //simpler way of accomplishing the code below this in comments
              counties[i].numPeopleSixtyOrHigher -= vaccinated1;
              counties[i].numPeopleFiftyFiveOrHigherWithCondition -= vaccinated2;
              
              //counties[i].numPeopleSixtyOrHigher -= vaccinated * (counties[i].numPeopleSixtyOrHigher/counties[i].populationOfCountyNotVaccinated);
              //counties[i].numPeopleFiftyFiveOrHigherWithCondition -= vaccinated * (counties[i].numPeopleFiftyFiveOrHigherWithCondition/counties[i].populationOfCountyNotVaccinated);
              break;
            case 4: // People Fifty To Sixty
              vaccinated =  (int)(proportion * counties[i].numPeopleFiftyToSixty);
              counties[i].numPeopleFiftyToSixty -= vaccinated;
              break;
            case 5: // People Eighteen To Fifty Five With Condition
              vaccinated =  (int)(proportion * counties[i].numPeopleEighteenToFiftyFiveWithCondition);
              counties[i].numPeopleEighteenToFiftyFiveWithCondition -= vaccinated;
              break;
            case 6: // People Eighteen To Fifty
              vaccinated =  (int)(proportion * counties[i].numPeopleEighteenToFifty);
              counties[i].numPeopleEighteenToFifty -= vaccinated;
              break;
          }

          //Total vaccinated per county.
          vaccinePerCounty[i] += vaccinated;

          //Total vaccinated all counties.
          totalVaccinated += vaccinePerCounty[i];
          counties[i].populationOfCountyNotVaccinated -= vaccinated;

          //updates the number of vaccines left over
          grpVaccines -= vaccinated;
        }

        //Distribute the left over vaccines to the remaining counties, if any left over.
        
        while (grpVaccines > 0)
        {
          vaccinePerCounty[j]++;
          totalVaccinated++;
          j = ((j + 1) % vaccinePerCounty.length);
          grpVaccines--;
        }

        //in the case of having left over vaccines grpVaccines will become 0
        availableVaccines = grpVaccines; 
      }


      totalPopulationNotVaccinated = 0;

      //in the end the number of people vaccinated per county in each group is printed as well as the total remaining that needs to be vaccinated in all of california after the groups receive a number of vaccines
      for(int i = 0; i < counties.length; i++){
          System.out.println("County " + (i+1) + " Vaccinated: " + vaccinePerCounty[i] + " - Total Remaining Unvaccinated After Current Group: " + counties[i].populationOfCountyNotVaccinated);
        totalPopulationNotVaccinated += counties[i].populationOfCountyNotVaccinated;
      }

      System.out.println(" ");

      //Printing the number of people in each group that need to be vaccinated
      //System.out.println(grpToPrint + " Needed to Vaccinate: " + (totalToVaccinate) );

      System.out.println(" ");
      System.out.println(grpToPrint + " Vaccinated: " + totalVaccinated);
      System.out.println(grpToPrint + " still to vaccinate: " + (totalToVaccinate - totalVaccinated));

      //Return the number of accines remaing after vaccination.
      return availableVaccines;
    }
}