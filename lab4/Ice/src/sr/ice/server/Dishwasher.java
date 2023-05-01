package sr.ice.server;

import Devices.*;
import com.zeroc.Ice.Current;

import java.util.ArrayList;


public class Dishwasher implements Washer {
    private final ArrayList<Program> programs;
    private int currentProgram;
    private PlannedProgram plannedProgram;

    public Dishwasher(){
        currentProgram = 0;

        programs = new ArrayList<Program>();
        programs.add(new Program(0, "None", 0, 0));
        programs.add(new Program(1, "Short", 30, 50));
        programs.add(new Program(2, "Medium", 60, 55));
        programs.add(new Program(3, "Long", 90, 60));
        programs.add(new Program(4, "Rinsing", 15, 30));

        plannedProgram = new PlannedProgram(programs.get(0), new Time(0,0));
    }



    @Override
    public int runWashing(int programId, Current current) {
        try{
            if(programId <= 0 || programId >= programs.size()){
                throw new ProgramError();
            }
            currentProgram = programId;
        } catch (ProgramError e) {
            System.out.println(e.message);
            return 0;
        }
        return currentProgram;
    }

    @Override
    public int planWashing(int programId, Time time, Current current) {
        try{
            if(programId <= 0 || programId >= programs.size()) {
                throw new ProgramError();
            }

            if(time.hour < 0 || time.hour >= 24 || time.minute < 0 || time.minute >= 60){
                throw new TimeError();
            }

            plannedProgram = new PlannedProgram(programs.get(programId), time);

        } catch (ProgramError e) {
            System.out.println(e.message);
            return 0;
        } catch (TimeError e) {
            System.out.println(e.message);
            return 0;
        }
        return plannedProgram.program.number;
    }

    @Override
    public int cancelPlanedWashing(Current current) {
        plannedProgram.time = new Time(0,0);
        int prev_program = plannedProgram.program.number;
        plannedProgram.program = programs.get(0);
        return prev_program;
    }

    @Override
    public PlannedProgram getPlannedProgram(Current current) {
        return plannedProgram;
    }

    @Override
    public Program programDetails(int programId, Current current) {
        try {
            if(programId <= 0 || programId >= programs.size()) {
                throw new ProgramError();
            }
        } catch (ProgramError e){
            System.out.println(e.message);
            return programs.get(0);
        }

        return programs.get(programId);
    }
}
