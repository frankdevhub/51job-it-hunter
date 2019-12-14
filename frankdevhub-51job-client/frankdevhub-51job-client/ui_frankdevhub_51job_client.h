#pragma once
/********************************************************************************
** Form generated from reading UI file 'frankdevhub_51job_client.ui'
**
** Created by: Qt User Interface Compiler version 5.10.0
**
** WARNING! All changes made in this file will be lost when recompiling UI file!
********************************************************************************/

#ifndef UI_FRANKDEVHUB_51JOB_CLIENT_H
#define UI_FRANKDEVHUB_51JOB_CLIENT_H

#include <QtCore/QVariant>
#include <QtWidgets/QAction>
#include <QtWidgets/QApplication>
#include <QtWidgets/QButtonGroup>
#include <QtWidgets/QHeaderView>
#include <QtWidgets/QMainWindow>
#include <QtWidgets/QMenu>
#include <QtWidgets/QMenuBar>
#include <QtWidgets/QToolBar>
#include <QtWidgets/QWidget>

QT_BEGIN_NAMESPACE

class Ui_frankdevhub_51job_clientClass
{
public:
	QAction *createNewDataFile;
	QAction *openDataFile;
	QAction *setServer;
	QAction *pingTest;
	QAction *networkTest;
	QAction *clearHistory;
	QAction *addressHelp;
	QAction *aboutInfo;
	QWidget *centralWidget;
	QToolBar *mainToolBar;
	QMenuBar *menuBar;
	QMenu *fileMenu;
	QMenu *settingsMenu;
	QMenu *helpMenu;

	void setupUi(QMainWindow *frankdevhub_51job_clientClass)
	{
		if (frankdevhub_51job_clientClass->objectName().isEmpty())
			frankdevhub_51job_clientClass->setObjectName(QStringLiteral("frankdevhub_51job_clientClass"));
		frankdevhub_51job_clientClass->resize(600, 400);
#ifndef QT_NO_ACCESSIBILITY
		frankdevhub_51job_clientClass->setAccessibleName(QStringLiteral(""));
#endif // QT_NO_ACCESSIBILITY
		frankdevhub_51job_clientClass->setAutoFillBackground(false);
		createNewDataFile = new QAction(frankdevhub_51job_clientClass);
		createNewDataFile->setObjectName(QStringLiteral("createNewDataFile"));
		openDataFile = new QAction(frankdevhub_51job_clientClass);
		openDataFile->setObjectName(QStringLiteral("openDataFile"));
		setServer = new QAction(frankdevhub_51job_clientClass);
		setServer->setObjectName(QStringLiteral("setServer"));
		pingTest = new QAction(frankdevhub_51job_clientClass);
		pingTest->setObjectName(QStringLiteral("pingTest"));
		networkTest = new QAction(frankdevhub_51job_clientClass);
		networkTest->setObjectName(QStringLiteral("networkTest"));
		clearHistory = new QAction(frankdevhub_51job_clientClass);
		clearHistory->setObjectName(QStringLiteral("clearHistory"));
		addressHelp = new QAction(frankdevhub_51job_clientClass);
		addressHelp->setObjectName(QStringLiteral("addressHelp"));
		aboutInfo = new QAction(frankdevhub_51job_clientClass);
		aboutInfo->setObjectName(QStringLiteral("aboutInfo"));
		centralWidget = new QWidget(frankdevhub_51job_clientClass);
		centralWidget->setObjectName(QStringLiteral("centralWidget"));
		frankdevhub_51job_clientClass->setCentralWidget(centralWidget);
		mainToolBar = new QToolBar(frankdevhub_51job_clientClass);
		mainToolBar->setObjectName(QStringLiteral("mainToolBar"));
		frankdevhub_51job_clientClass->addToolBar(Qt::TopToolBarArea, mainToolBar);
		menuBar = new QMenuBar(frankdevhub_51job_clientClass);
		menuBar->setObjectName(QStringLiteral("menuBar"));
		menuBar->setGeometry(QRect(0, 0, 600, 23));
		fileMenu = new QMenu(menuBar);
		fileMenu->setObjectName(QStringLiteral("fileMenu"));
		settingsMenu = new QMenu(menuBar);
		settingsMenu->setObjectName(QStringLiteral("settingsMenu"));
		helpMenu = new QMenu(menuBar);
		helpMenu->setObjectName(QStringLiteral("helpMenu"));
		frankdevhub_51job_clientClass->setMenuBar(menuBar);

		menuBar->addAction(fileMenu->menuAction());
		menuBar->addAction(settingsMenu->menuAction());
		menuBar->addAction(helpMenu->menuAction());
		fileMenu->addAction(createNewDataFile);
		fileMenu->addAction(openDataFile);
		settingsMenu->addAction(setServer);
		settingsMenu->addAction(pingTest);
		settingsMenu->addAction(networkTest);
		settingsMenu->addAction(clearHistory);
		helpMenu->addAction(addressHelp);
		helpMenu->addAction(aboutInfo);

		retranslateUi(frankdevhub_51job_clientClass);

		QMetaObject::connectSlotsByName(frankdevhub_51job_clientClass);
	} // setupUi

	void retranslateUi(QMainWindow *frankdevhub_51job_clientClass)
	{
		frankdevhub_51job_clientClass->setWindowTitle(QApplication::translate("frankdevhub_51job_clientClass", "Frankdevhub Automation", nullptr));
#ifndef QT_NO_WHATSTHIS
		frankdevhub_51job_clientClass->setWhatsThis(QString());
#endif // QT_NO_WHATSTHIS
		createNewDataFile->setText(QApplication::translate("frankdevhub_51job_clientClass", "\346\226\260\345\273\272", nullptr));
		openDataFile->setText(QApplication::translate("frankdevhub_51job_clientClass", "\346\211\223\345\274\200\351\205\215\347\275\256\346\226\207\344\273\266", nullptr));
		setServer->setText(QApplication::translate("frankdevhub_51job_clientClass", "\350\256\276\347\275\256\346\234\215\345\212\241\345\231\250", nullptr));
		pingTest->setText(QApplication::translate("frankdevhub_51job_clientClass", "\346\265\213\350\257\225\346\234\215\345\212\241\345\231\250\350\277\236\346\216\245\347\212\266\346\200\201", nullptr));
		networkTest->setText(QApplication::translate("frankdevhub_51job_clientClass", "\347\275\221\351\200\237\350\256\276\347\275\256", nullptr));
		clearHistory->setText(QApplication::translate("frankdevhub_51job_clientClass", "\346\270\205\347\251\272\345\216\206\345\217\262\346\225\260\346\215\256", nullptr));
		addressHelp->setText(QApplication::translate("frankdevhub_51job_clientClass", "\345\205\254\345\217\270\345\234\260\345\235\200\346\237\245\350\257\242", nullptr));
		aboutInfo->setText(QApplication::translate("frankdevhub_51job_clientClass", "\345\205\263\344\272\216", nullptr));
#ifndef QT_NO_WHATSTHIS
		fileMenu->setWhatsThis(QString());
#endif // QT_NO_WHATSTHIS
		fileMenu->setTitle(QApplication::translate("frankdevhub_51job_clientClass", "\346\226\207\344\273\266(F)", nullptr));
		settingsMenu->setTitle(QApplication::translate("frankdevhub_51job_clientClass", "\350\256\276\347\275\256(S)", nullptr));
		helpMenu->setTitle(QApplication::translate("frankdevhub_51job_clientClass", "\345\270\256\345\212\251(H)", nullptr));
	} // retranslateUi

};

namespace Ui {
	class frankdevhub_51job_clientClass : public Ui_frankdevhub_51job_clientClass {};
} // namespace Ui

QT_END_NAMESPACE

#endif // UI_FRANKDEVHUB_51JOB_CLIENT_H
