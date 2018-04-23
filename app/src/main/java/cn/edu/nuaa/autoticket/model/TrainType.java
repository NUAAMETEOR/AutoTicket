/**
 * @ClassName: TrainType.java
 * @Description:
 * @author Meteor
 * @version V1.0
 * @Date 2018/4/8 21:57
 */
package cn.edu.nuaa.autoticket.model;

import cn.edu.nuaa.autoticket.R;
import cn.edu.nuaa.autoticket.component.EnumInterfaceForTicket;

public enum TrainType implements EnumInterfaceForTicket {
    ALL_TRAIN {
        @Override
        public int getResourceId() {
            return R.string.all_train;
        }

        @Override
        public String getFilterString() {
            return ".+";
        }
    },HIGH_SPEED_TRAIN {
        @Override
        public int getResourceId() {
            return R.string.high_speed_train;
        }

        @Override
        public String getFilterString() {
            return "^(D|G).+";
        }
    },NORMAL_TRAIN {
        @Override
        public int getResourceId() {
            return R.string.normal_train;
        }

        @Override
        public String getFilterString() {
            return "^[^[DG]].+";
        }
    }
}
