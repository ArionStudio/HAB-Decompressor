package huffman.decompressor.hab.decompiler;

import java.util.ArrayList;
import java.util.Arrays;

public class GetOpt {
    private static GetOpt instance;
    private static String [] optArgs;
    private static ArrayList<String> singleArgs;
    private static ArrayList<String> multiArgs;
    private static ArrayList<String> existingArgs;

    public static class OptExeption extends Exception{
        private final int errorCode;

        public OptExeption(String messege, int errorCode){
            super(messege);
            this.errorCode = errorCode;


        }
        public int getErrorCode(){
            return errorCode;
        }
    }

    public GetOpt(String [] all, String [] single, String [] multi) throws OptExeption {
        optArgs = all;
        singleArgs = new ArrayList<>(Arrays.asList(single));
        multiArgs = new ArrayList<>(Arrays.asList(multi));
        existingArgs = new ArrayList<>();

        // Check optArgs array with regex passed
        for (int i = 0; i < optArgs.length; i++) {
            // All options have to start with '-'
            if(optArgs[i].charAt(0) != '-'){
                throw new OptExeption("All arguments should have starts with '-'", 3);
            }else{
                optArgs[i] = optArgs[i].substring(1);
            }
            if(singleArgs.contains(optArgs[i])){
                if(i + 1 != optArgs.length){
                    // single arg have option
                    if(multiArgs.contains(optArgs[i + 1])){
                        throw new OptExeption("'Single' arguments can't have options", 2);
                    }else{
                        existingArgs.add(optArgs[i].substring(1));
                    }
                }else{
                    existingArgs.add(optArgs[i].substring(1));
                }
                if(optArgs[i].equals("h")){
                    printHelp();
                    throw new OptExeption("", 0);
                }
            }else if(multiArgs.contains(optArgs[i])){
                if(i + 1 != optArgs.length){
                    if(!multiArgs.contains(optArgs[i + 1])){
                        // Option of arg can't start with '-'
                        if(optArgs[i + 1].charAt(0) == '-'){
                            throw new OptExeption("'Multiple' arguments options can't start with '-'", 3);
                        }else{
                            existingArgs.add(optArgs[i].substring(1));
                            i++;
                        }
                    }else{
                        throw new OptExeption("'Multiple' arguments must have option", 2);
                    }
                }else{
                    // multiarg dont have option
                    throw new OptExeption("'Multiple' arguments must have option", 2);
                }
            }else{
                throw new OptExeption("It's not argument passed in regex arrays", 1);
            }
        }
    }


    public boolean exist(String argName){
        return  existingArgs.contains(argName);
    }

    public String getOption(String argName) throws OptExeption{
        if(existingArgs.contains(argName) && multiArgs.contains(argName)){
            for(int i = 0; i < optArgs.length; i++){
                if(singleArgs.contains(optArgs[i])){
                    throw new OptExeption("This argument don't have option", 2);
                }
                if(optArgs[i].equals(argName)){
                    return optArgs[i + 1];
                }
                i++;
            }
            throw new OptExeption("This argument doesn't exist", 1);
        }
        throw new OptExeption("This argument doesn't exist", 1);
    }



    public static void printHelp(){
        System.out.println( "\n\n\nProgram usage instruction\n\n"
                + "Arguments: \n"
                + "\t[-i input-file]\n"
                + "\t[-o output-file]\n"
                + "\t[-d decryption-password]\n"
                + "\t\tDencrypt file with this password\n"
                + "\t[-m]\n"
                + "\t\tMore info during program run\n"
                + "\t[-h]\n"
                + "\t\tPrint this help message!\n");
    }
}
