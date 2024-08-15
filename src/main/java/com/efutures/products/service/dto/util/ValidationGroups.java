package com.efutures.products.service.dto.util;

import javax.validation.groups.Default;

public interface ValidationGroups {

    interface Update extends Default {
    }

    interface Save extends Default {
    }

    interface Delete extends Default {
    }
}
