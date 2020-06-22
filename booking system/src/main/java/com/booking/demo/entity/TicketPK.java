package com.booking.demo.entity;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Setter
@Getter
public class TicketPK implements Serializable {
    private long uid;
    private String fid;

    @Override
    public boolean equals(Object o) {
        if (o instanceof TicketPK) {
            TicketPK pk = (TicketPK) o;
            return this.uid == pk.getUid() && this.fid.equals(pk.getFid());
        }
        return false;
    }

    @Override
    public int hashCode() {
        return (int) (this.fid.hashCode() ^ this.uid);
    }
}
