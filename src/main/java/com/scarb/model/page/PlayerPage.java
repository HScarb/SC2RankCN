package com.scarb.model.page;

import com.scarb.model.Player;
import com.scarb.pagination.Page;

/**
 * Created by Scarb on 9/14/2016.
 */
public class PlayerPage extends Player{
    private Page page = new Page();

    public Page getPage() {
        return page;
    }

    public void setPage(Page page) {
        this.page = page;
    }
}
