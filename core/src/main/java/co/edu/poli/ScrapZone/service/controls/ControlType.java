package co.edu.poli.ScrapZone.service.controls;

import com.badlogic.gdx.controllers.Controller;
import com.badlogic.gdx.controllers.Controllers;
import com.badlogic.gdx.utils.Array;
import com.github.czyzby.kiwi.util.gdx.collection.GdxArrays;
import co.edu.poli.ScrapZone.configuration.preferences.ControlsData;
import co.edu.poli.ScrapZone.service.controls.impl.GamePadControl;
import co.edu.poli.ScrapZone.service.controls.impl.InactiveControl;
import co.edu.poli.ScrapZone.service.controls.impl.KeyboardControl;
import co.edu.poli.ScrapZone.service.controls.impl.TouchControl;

/** Represents all types of available input sources. */
public enum ControlType {
    TOUCH {
        @Override
        public Control create(final ControlsData data) {
            return new TouchControl();
        }
    },
    KEYBOARD {
        @Override
        public Control create(final ControlsData data) {
            final Control control = new KeyboardControl();
            control.copy(data);
            return control;
        }
    },
    PAD {
        @Override
        public Control create(final ControlsData data) {
            final Array<Controller> controllers = Controllers.getControllers();
            if (GdxArrays.isEmpty(controllers) || !GdxArrays.isIndexValid(controllers, data.index)) {
                // Controller unavailable. Fallback to inactive.
                return new InactiveControl();
            }
            final Control control = new GamePadControl(controllers.get(data.index));
            control.copy(data);
            return control;
        }
    },
    INACTIVE {
        @Override
        public Control create(final ControlsData data) {
            return new InactiveControl();
        }
    };

    /** @param data serialized controls.
     * @return deserialized controller. */
    public abstract Control create(ControlsData data);
}