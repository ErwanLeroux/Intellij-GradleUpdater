/*
 * MIT License
 *
 * Copyright (c) 2020 Michael Rittmeister
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package me.schlaubi.intellij_gradle_version_checker.inspection.dependencies.inconstant_format

import com.intellij.codeInspection.LocalQuickFix
import com.intellij.codeInspection.ProblemDescriptor
import com.intellij.openapi.project.Project
import me.schlaubi.intellij_gradle_version_checker.GradleUpdaterBundle
import me.schlaubi.intellij_gradle_version_checker.dependency_format.DependencyFormat
import me.schlaubi.intellij_gradle_version_checker.settings.ApplicationGradleVersionSettings
import me.schlaubi.intellij_gradle_version_checker.settings.GradleVersionSettings
import me.schlaubi.intellij_gradle_version_checker.util.name

sealed class AbstractUpdateSettingsQuickfix(
    private val dependencyFormat: DependencyFormat,
    private val settings: GradleVersionSettings
) : LocalQuickFix {
    override fun applyFix(project: Project, descriptor: ProblemDescriptor) {
        settings.dependencyFormat = dependencyFormat.name
    }
}

class ApplicationUpdateSettingsQuickfix(
    dependencyFormat: DependencyFormat
) : AbstractUpdateSettingsQuickfix(dependencyFormat, ApplicationGradleVersionSettings) {
    override fun getFamilyName(): String =
        GradleUpdaterBundle.getMessage("inspection.inconsistent_format.quickfix.update_app")
}

class ProjectUpdateSettingsQuickfix(
    dependencyFormat: DependencyFormat,
    settings: GradleVersionSettings
) : AbstractUpdateSettingsQuickfix(dependencyFormat, settings) {
    override fun getFamilyName(): String =
        GradleUpdaterBundle.getMessage("inspection.inconsistent_format.quickfix.update_project")
}
