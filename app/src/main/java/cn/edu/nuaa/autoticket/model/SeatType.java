package cn.edu.nuaa.autoticket.model;

import cn.edu.nuaa.autoticket.R;
import cn.edu.nuaa.autoticket.component.EnumInterfaceForTicket;

public enum SeatType implements EnumInterfaceForTicket {

    ALL {
        @Override
        public int getResourceId() {
            return R.string.seat_all;
        }
    }, COMMERCIAL {
        @Override
        public int getResourceId() {
            return R.string.seat_commercial;
        }
    }, SPECIAL {
        @Override
        public int getResourceId() {
            return R.string.seat_special;
        }
    }, ADVANCED {
        @Override
        public int getResourceId() {
            return R.string.seat_advanced;
        }
    }, NORMAL {
        @Override
        public int getResourceId() {
            return R.string.seat_normal;
        }
    }, ADVANCE_SOFT_ {
        @Override
        public int getResourceId() {
            return R.string.seat_advance_soft_;
        }
    }, SOFT_ {
        @Override
        public int getResourceId() {
            return R.string.seat_soft_;
        }
    }, HARD_ {
        @Override
        public int getResourceId() {
            return R.string.seat_hard_;
        }
    }, SOFT {
        @Override
        public int getResourceId() {
            return R.string.seat_soft;
        }
    }, HARD {
        @Override
        public int getResourceId() {
            return R.string.seat_hard;
        }
    }
}
