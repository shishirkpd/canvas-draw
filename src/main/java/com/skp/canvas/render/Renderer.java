package com.skp.canvas.render;

import com.skp.canvas.processor.Drawer;

public interface Renderer {

    void renderContent(String desc);

    void renderNewLine(String desc);

    void close();

    void render(Drawer drawer);
}
