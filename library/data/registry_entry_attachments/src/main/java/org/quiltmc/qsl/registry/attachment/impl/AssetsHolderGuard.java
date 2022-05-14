/*
 * Copyright 2021 QuiltMC
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.quiltmc.qsl.registry.attachment.impl;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import org.jetbrains.annotations.ApiStatus;

import net.minecraft.util.registry.Registry;

/**
 * Simple guard class that prevents access to the
 * {@linkplain RegistryEntryAttachmentHolder#getAssets(Registry) assets-based <code>RegistryAttachmentHolder</code> instance}
 * in a dedicated server environment.
 */
@ApiStatus.Internal
public final class AssetsHolderGuard {
	private static boolean allowed = false;

	@Environment(EnvType.CLIENT)
	public static void setAccessAllowed() {
		allowed = true;
	}

	public static boolean isAccessAllowed() {
		return allowed;
	}

	public static void assertAccessAllowed() {
		if (!allowed) {
			throw new IllegalStateException("Access to assets-based registry attachments is not allowed here!");
		}
	}
}