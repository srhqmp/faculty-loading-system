package edu.university.facultyloading.view;

import edu.university.facultyloading.util.OutputFormatter;
import edu.university.facultyloading.util.PromptMessageView;

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
            PromptMessageView.header(line);
        }

        System.out.println();
        PromptMessageView.choices("╔══════════════════════════════╗");
        PromptMessageView.choices("║           MAIN MENU          ║");
        PromptMessageView.choices("╠══════════════════════════════╣");
        PromptMessageView.choices("║ 1. Login                     ║");
        PromptMessageView.choices("║ 2. Register                  ║");
        PromptMessageView.choices("║ 0. Exit                      ║");
        PromptMessageView.choices("╚══════════════════════════════╝");
        System.out.print(OutputFormatter.centerString("Choose an option: "));
    }
}
