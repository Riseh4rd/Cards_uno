import java.util.Arrays;
import java.util.Objects;
import java.util.Random;
import java.util.Scanner;

class RAZDACHA//i am a server server server server server server
{
    public static final String RED = "\033[1;91m";   // RED
    public static final String GREEN = "\033[1;92m"; // GREEN
    public static final String YELLOW = "\033[1;93m";// YELLOW
    public static final String BLUE = "\033[1;94m";  // BLUE
    public static final String PURPLE = "\033[1;95m";// PURPLE
    public static final String CYAN = "\033[1;96m";  // CYAN

    public static final String RESET = "\033[0m";  // Text Reset
    private static String[] MASSKOLODA;
    private static int activeCard = 0;
    private static String[] Player1;
    private static String[] Player2;
    private static String[] Player3;
    private static String[] Player4;
    private static String[] Player5;
    private static String[] Player6;



    public static void shuffle(String[] in){
        Random r = new Random();
        int index = 0;
        String temp = "";
        for (int i = 0; i < in.length; i++) {
            index = r.nextInt(in.length);
            temp = in[index];
            in[index] = in[i];
            in[i] = temp;
        }
    }

    public static void PlayerToss(String[] in, int activeCard){
        Random r = new Random();
        System.out.println();
        for (int i = 0; i <6; i++) {in[i] = MASSKOLODA[activeCard+i];MASSKOLODA[activeCard+i] = null;}
        activeCard=activeCard+6;
        System.out.print("Player"+activeCard/6+" "+Arrays.toString(in));
        System.out.println("\n");
    }

    public static int HowCardsIHaw(String[] Mass){
        int playercardnumber=0;
        for (int i = 0; i <Mass.length ; i++) {if (Objects.equals(Mass[i], null)){for (int j = i; j <Mass.length-1 ; j++) {Mass[j] = Mass[j+1];}}}
        boolean a = true;
        while(a){if (Objects.equals(Mass[playercardnumber],null)){a=false;break;}playercardnumber++;}
        return playercardnumber;
    }
    public static int ActiveCardNow(String[] Mass){
         boolean a = true;
        while(a){if (!Objects.equals(Mass[activeCard],null)){a=false;break;}activeCard++;}
        return activeCard;
    }

    public static void PickNewCard(String[] Mass,String[] Masscoloda,int playercardnumber,int activeCard ){
        playercardnumber=HowCardsIHaw(Mass);
        activeCard=ActiveCardNow(Masscoloda);
        Mass[playercardnumber]=Masscoloda[activeCard];Masscoloda[activeCard]=null;activeCard++;playercardnumber++;
        System.out.println("New Card Picked!"+playercardnumber+". "+activeCard);
        System.out.println(Arrays.toString(Mass));
    }
    public static String PlayerTurn(String[] Mass,String CardOnTable,String[] Masscoloda){
        boolean CanBeat = false;
        while (!CanBeat){
        System.out.println(Arrays.toString(Mass));
        Scanner in = new Scanner(System.in);
         String input = in.nextLine();
        if(input.equalsIgnoreCase("P")){int playercardnumber=0;playercardnumber=HowCardsIHaw(Mass);
            System.out.println(playercardnumber);
            PickNewCard(Mass,Masscoloda,playercardnumber,activeCard);
            CanBeat=false;break;}
        else {
        int InputCardNum;
        int playercardnumber=0;
        InputCardNum = (in.nextInt()-1);
        if(InputCardNum == -1){InputCardNum++;}

        CanBeat=CanIAttack(Mass[InputCardNum],CardOnTable);

        if(CanBeat){CardOnTable = Mass[InputCardNum];
            Mass[InputCardNum] =null;

            playercardnumber=HowCardsIHaw(Mass);System.out.println(playercardnumber);
            System.out.println(CardOnTable);}

        else {System.out.println("You Cant Beat This!"+"\n"+CardOnTable);}}}

        return CardOnTable;
    }

    public static boolean CanIAttack (String AttackCard,String DefendCard){
        if(DefendCard == null)
        {return true;}
        else{
        String[] AttackCardSplit = AttackCard.split("\\.");
        String[] DefendCardSplit = DefendCard.split("\\.");
            System.out.println();
        System.out.println(AttackCard);
        System.out.println(DefendCard);
        System.out.println(Arrays.toString(AttackCardSplit));
        System.out.println(Arrays.toString(DefendCardSplit));
            System.out.println();
        return (AttackCardSplit[0].equals(DefendCardSplit[0])
              ||AttackCardSplit[1].equals(DefendCardSplit[1])
              ||AttackCardSplit[2].equals(DefendCardSplit[2]));
        }
    }


    public static String[][] getColoda(){
        return new String[][] {MASSKOLODA, Player1, Player2, Player3, Player4,Player5,Player6};
    }




    public RAZDACHA() {
        MASSKOLODA  = new String[216];
        String[] master= new String[] {RED+"",YELLOW+"",GREEN+"",BLUE+"",PURPLE+"",CYAN+""};
        String[] typer= new String[]{"1","2","3","4","5","6"};
        String[] figure= new String[]{"KRUG"+RESET,"SQUARER"+RESET,"TRIANGLE"+RESET,"HEX"+RESET,"nothing"+RESET,"CROSS"+RESET};//●■▲⬢〤


        int mast = 0;
        int type = 0;
        int figur = 0;


        for (int i = 0; i <216; i++) {

            MASSKOLODA[i] = (master[mast]+"."+typer[type]+"."+figure[figur]);
            mast++;

            if(mast==6){mast = 0;}

            if((i%6)==0&i!=0){System.out.println();type++;}

                if(type==6){type=0;}

            if((i%36)==0&i!=0){System.out.println();figur++;}

                if(figur==6){figur=0;}
            System.out.print("\t"+MASSKOLODA[i]+"\t");
        }

        System.out.println("\n".repeat(3));

        Player1 = new String[20];
        Player2 = new String[20];
        Player3 = new String[20];
        Player4 = new String[20];
        Player5 = new String[20];
        Player6 = new String[20];
        shuffle(MASSKOLODA);

        for (int i = 0; i <216; i++) {mast++;if(mast==6){mast = 0;}if((i%6)==0){System.out.println();type++;if(type==6){type=0;}}if((i%36)==0){System.out.println();figur++;if(figur==6){figur=0;}}System.out.print(MASSKOLODA[i]+"\t");}

        Random r = new Random();
        String CardOnTable = null;
        int HowManyPlayers = r.nextInt(4)+2;
        int playernum = HowManyPlayers;
        System.out.println("playernum "+HowManyPlayers);

        int activeCard =0;
        PlayerToss(Player1,activeCard);activeCard=activeCard+6;
        PlayerToss(Player2,activeCard);activeCard=activeCard+6;
        if(playernum >= 3){PlayerToss(Player3,activeCard);activeCard=activeCard+6;}
        if(playernum >= 4){PlayerToss(Player4,activeCard);activeCard=activeCard+6;}
        if(playernum >= 5){PlayerToss(Player5,activeCard);activeCard=activeCard+6;}
        if(playernum == 6){PlayerToss(Player6,activeCard);activeCard=activeCard+6;}


        System.out.println("GAME STARTED!\n".repeat(3));

        //START!
        playernum = r.nextInt(HowManyPlayers)+1;
        if(7 == playernum){playernum--;}
        System.out.println("playernum"+playernum);
        boolean GameEnd=false;
       while(!GameEnd){
           if(playernum == 1) {CardOnTable=PlayerTurn(Player1,CardOnTable,MASSKOLODA);
               if(Player1[0]==null)
               {
                   GameEnd=true;
                   System.out.println("Player 1 Wins!");
                   break;
               }
           }
           if(playernum == 2) {CardOnTable=PlayerTurn(Player2,CardOnTable,MASSKOLODA);if(Player2[0]==null){GameEnd=true;System.out.println("Player 2 Wins!");break;}}
           if(playernum == 3) {CardOnTable=PlayerTurn(Player3,CardOnTable,MASSKOLODA);if(Player3[0]==null){GameEnd=true;System.out.println("Player 3 Wins!");break;}}
           if(playernum == 4) {CardOnTable=PlayerTurn(Player4,CardOnTable,MASSKOLODA);if(Player4[0]==null){GameEnd=true;System.out.println("Player 4 Wins!");break;}}
           if(playernum == 5) {CardOnTable=PlayerTurn(Player5,CardOnTable,MASSKOLODA);if(Player5[0]==null){GameEnd=true;System.out.println("Player 5 Wins!");break;}}
           if(playernum == 6) {CardOnTable=PlayerTurn(Player6,CardOnTable,MASSKOLODA);if(Player6[0]==null){GameEnd=true;System.out.println("Player 6 Wins!");break;}}
         playernum++;
        if (playernum ==(HowManyPlayers+1)){playernum = 0;}


       }
    }
    }


