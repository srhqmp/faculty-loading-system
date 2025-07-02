package edu.university.facultyloading.view;

import edu.university.facultyloading.util.OutputFormatter;
import edu.university.facultyloading.util.PromptMessage;

public class MainMenuView {
    public void show() {
        String[] header = {
                "╔════════════════════════════════════════════════════════════════════╗",
                "║                            WELCOME TO                              ║",
                "║                                                                    ║",
                "║    ███████╗ █████╗  ██████╗██╗   ██╗██╗     ████████╗██╗   ██╗     ║",
                "║    ██╔════╝██╔══██╗██╔════╝██║   ██║██║     ╚══██╔══╝╚██╗ ██╔╝     ║",
                "║    █████╗  ███████║██║     ██║   ██║██║        ██║    ╚████╔╝      ║",
                "║    ██╔══╝  ██╔══██║██║     ██║   ██║██║        ██║     ╚██╔╝       ║",
                "║    ██║     ██║  ██║╚██████╗╚██████╔╝███████╗   ██║      ██║        ║",
                "║    ╚═╝     ╚═╝  ╚═╝ ╚═════╝ ╚═════╝ ╚══════╝   ╚═╝      ╚═╝        ║",
                "║                                                                    ║",
                "║                           LOADING SYSTEM                           ║",
                "╚════════════════════════════════════════════════════════════════════╝"
        };

        for (String line : header) {
            PromptMessage.header(line);
        }

        System.out.println();
        PromptMessage.choices("╔══════════════════════════════╗");
        PromptMessage.choices("║           MAIN MENU          ║");
        PromptMessage.choices("╠══════════════════════════════╣");
        PromptMessage.choices("║ 1. Login                     ║");
        PromptMessage.choices("║ 2. Register                  ║");
        PromptMessage.choices("║ 0. Exit                      ║");
        PromptMessage.choices("╚══════════════════════════════╝");
        System.out.print(OutputFormatter.centerString("Choose an option: "));
    }
}
