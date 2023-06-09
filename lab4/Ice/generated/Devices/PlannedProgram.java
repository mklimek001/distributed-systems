//
// Copyright (c) ZeroC, Inc. All rights reserved.
//
//
// Ice version 3.7.9
//
// <auto-generated>
//
// Generated from file `smarthome.ice'
//
// Warning: do not edit this file.
//
// </auto-generated>
//

package Devices;

public class PlannedProgram implements java.lang.Cloneable,
                                       java.io.Serializable
{
    public Program program;

    public Time time;

    public PlannedProgram()
    {
        this.program = new Program();
        this.time = new Time();
    }

    public PlannedProgram(Program program, Time time)
    {
        this.program = program;
        this.time = time;
    }

    public boolean equals(java.lang.Object rhs)
    {
        if(this == rhs)
        {
            return true;
        }
        PlannedProgram r = null;
        if(rhs instanceof PlannedProgram)
        {
            r = (PlannedProgram)rhs;
        }

        if(r != null)
        {
            if(this.program != r.program)
            {
                if(this.program == null || r.program == null || !this.program.equals(r.program))
                {
                    return false;
                }
            }
            if(this.time != r.time)
            {
                if(this.time == null || r.time == null || !this.time.equals(r.time))
                {
                    return false;
                }
            }

            return true;
        }

        return false;
    }

    public int hashCode()
    {
        int h_ = 5381;
        h_ = com.zeroc.IceInternal.HashUtil.hashAdd(h_, "::Devices::PlannedProgram");
        h_ = com.zeroc.IceInternal.HashUtil.hashAdd(h_, program);
        h_ = com.zeroc.IceInternal.HashUtil.hashAdd(h_, time);
        return h_;
    }

    public PlannedProgram clone()
    {
        PlannedProgram c = null;
        try
        {
            c = (PlannedProgram)super.clone();
        }
        catch(CloneNotSupportedException ex)
        {
            assert false; // impossible
        }
        return c;
    }

    public void ice_writeMembers(com.zeroc.Ice.OutputStream ostr)
    {
        Program.ice_write(ostr, this.program);
        Time.ice_write(ostr, this.time);
    }

    public void ice_readMembers(com.zeroc.Ice.InputStream istr)
    {
        this.program = Program.ice_read(istr);
        this.time = Time.ice_read(istr);
    }

    static public void ice_write(com.zeroc.Ice.OutputStream ostr, PlannedProgram v)
    {
        if(v == null)
        {
            _nullMarshalValue.ice_writeMembers(ostr);
        }
        else
        {
            v.ice_writeMembers(ostr);
        }
    }

    static public PlannedProgram ice_read(com.zeroc.Ice.InputStream istr)
    {
        PlannedProgram v = new PlannedProgram();
        v.ice_readMembers(istr);
        return v;
    }

    static public void ice_write(com.zeroc.Ice.OutputStream ostr, int tag, java.util.Optional<PlannedProgram> v)
    {
        if(v != null && v.isPresent())
        {
            ice_write(ostr, tag, v.get());
        }
    }

    static public void ice_write(com.zeroc.Ice.OutputStream ostr, int tag, PlannedProgram v)
    {
        if(ostr.writeOptional(tag, com.zeroc.Ice.OptionalFormat.FSize))
        {
            int pos = ostr.startSize();
            ice_write(ostr, v);
            ostr.endSize(pos);
        }
    }

    static public java.util.Optional<PlannedProgram> ice_read(com.zeroc.Ice.InputStream istr, int tag)
    {
        if(istr.readOptional(tag, com.zeroc.Ice.OptionalFormat.FSize))
        {
            istr.skip(4);
            return java.util.Optional.of(PlannedProgram.ice_read(istr));
        }
        else
        {
            return java.util.Optional.empty();
        }
    }

    private static final PlannedProgram _nullMarshalValue = new PlannedProgram();

    /** @hidden */
    public static final long serialVersionUID = -1260733655L;
}
